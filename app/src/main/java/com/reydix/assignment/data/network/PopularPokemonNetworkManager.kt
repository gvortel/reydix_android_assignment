package com.reydix.assignment.data.network

import android.util.Log
import com.reydix.assignment.data.network.model.PokemonDetailsResponse
import com.reydix.assignment.data.network.model.PopularPokemonResponse
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse


class PopularPokemonNetworkManager(private val apiService: PopularPokemonService) {

    fun getPopularPokemonAndPokemonDetailsAsync(): Deferred<List<PokemonDetailsResponse?>?> =
        CoroutineScope(Dispatchers.IO).async {
            try {
                val response = apiService.getPopularPokemon().awaitResponse()
                if (response.isSuccessful) {
                    val pokemonDetailsResponseList = mutableListOf<PokemonDetailsResponse?>()

                    response.body()?.results?.forEach { pokemon ->
                        val splitUrl: List<String>? = pokemon.url?.split("/")
                        val pokemonId = splitUrl?.getOrNull(splitUrl.lastIndex - 1)

                        val pokemonDetailsResponse = fetchPokemonDetails(pokemonId)
                        pokemonDetailsResponseList.add(pokemonDetailsResponse)
                    }

                    return@async pokemonDetailsResponseList.toList()
                } else {
                    // Handle error
                    return@async null
                }
            } catch (e: Exception) {
                // Handle network or API call failure
                return@async null
            }
    }

    private suspend fun fetchPokemonDetails(pokeId: String?): PokemonDetailsResponse? =
        withContext(Dispatchers.IO) {
            pokeId ?: return@withContext null

            try {
                val response = apiService.getPokemonDetails(pokeId).awaitResponse()
                if (response.isSuccessful) {
                    return@withContext response.body()
                } else {
                    // Handle error
                    return@withContext null
                }
            } catch (e: Exception) {
                // Handle network or API call failure
                return@withContext null
            }
    }
}