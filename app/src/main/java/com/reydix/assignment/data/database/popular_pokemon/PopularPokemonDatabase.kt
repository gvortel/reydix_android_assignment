package com.reydix.assignment.data.database.popular_pokemon

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reydix.assignment.data.database.popular_pokemon.entities.PokemonItem


@Database(
    entities = [PokemonItem::class],
    version = 2,
    exportSchema = false
)
abstract class PopularPokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PopularPokemonDao
}



