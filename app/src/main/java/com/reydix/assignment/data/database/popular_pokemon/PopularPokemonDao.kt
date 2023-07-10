package com.reydix.assignment.data.database.popular_pokemon

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.reydix.assignment.data.database.base.BaseDao
import com.reydix.assignment.data.database.popular_pokemon.entities.PokemonItem


@Dao
interface PopularPokemonDao : BaseDao<PokemonItem> {


    @Query("SELECT * FROM pokemon_item_table")
    fun getPokemonList(): List<PokemonItem>


    @Query("SELECT * FROM pokemon_item_table")
    fun getPokemonListLiveData(): LiveData<List<PokemonItem>>


    @Query("DELETE FROM pokemon_item_table")
    fun nukeTable()

    @Transaction
    fun insertAllOnInit(items: List<PokemonItem>): List<Long> {
        nukeTable()
        return insertAll(items)
    }

}