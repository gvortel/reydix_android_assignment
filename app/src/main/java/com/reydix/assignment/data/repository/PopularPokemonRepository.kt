package com.reydix.assignment.data.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.reydix.assignment.data.network.base.ResponseListener
import com.reydix.assignment.data.network.base.ResponseListenerIsAttached
import com.reydix.assignment.data.database.base.RepositoryProxyGet
import com.reydix.assignment.data.database.popular_pokemon.PopularPokemonDao
import com.reydix.assignment.data.database.popular_pokemon.entities.PokemonItem
import com.reydix.assignment.data.network.PopularPokemonNetworkManager
import com.reydix.assignment.data.network.model.PokemonDetailsResponse
import com.reydix.assignment.data.network.model.toListPokemonItem
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@Module
@InstallIn(SingletonComponent::class)
class PopularPokemonGetRepository @Inject constructor(
    private val application: Application,
    private val popularPokemonNetworkManager: PopularPokemonNetworkManager,
    private val pokemonDao: PopularPokemonDao
    ) {

    private fun processException(e: Throwable) {
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(application, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun getPopularPokemon(
        forceApi: Boolean = false,
        callback: ResponseListener<Any?>? = null,
        responseListenerIsAttached: ResponseListenerIsAttached?
    ): LiveData<List<PokemonItem>> {

        val getPopularPokemons = GetPopularPokemons(
            pokemonDao,
            popularPokemonNetworkManager
        )

        getPopularPokemons.run(
            forceApi,
            callback,
            ::processException,
            responseListenerIsAttached
        )

        return pokemonDao.getPokemonListLiveData()
    }
}


open class GetPopularPokemons(
    override val dao: PopularPokemonDao,
    private val networkManager: PopularPokemonNetworkManager,
) : RepositoryProxyGet<PokemonItem, List<PokemonDetailsResponse?>?>() {

    override fun getItemsFromDb(): List<PokemonItem>?  =
        dao.getPokemonList()


    override fun getItemsFromApiAsync(): Deferred<List<PokemonDetailsResponse?>?>? =
        networkManager.getPopularPokemonAndPokemonDetailsAsync()


    override fun transformApiItemsToDbItems(
        response: List<PokemonDetailsResponse?>?,
        dbItems: List<PokemonItem>?
    ): List<PokemonItem>? =
        response.toListPokemonItem()


    override fun insertItemsOnDb(items: List<PokemonItem>?) {
        items?.run {
            dao.insertAllOnInit(this)
        }
    }
}