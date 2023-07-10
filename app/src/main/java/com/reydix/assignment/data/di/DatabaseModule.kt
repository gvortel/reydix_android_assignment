package com.reydix.assignment.data.di

import android.content.Context
import androidx.room.Room
import com.reydix.assignment.data.database.popular_pokemon.PopularPokemonDatabase
import com.reydix.assignment.data.database.popular_pokemon.PopularPokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun providesPopularPokemonDatabase(
        @ApplicationContext appContext: Context
    ): PopularPokemonDatabase =
        Room.databaseBuilder(
            appContext,
            PopularPokemonDatabase::class.java,
            "REYDIX_POKEMON_DB"
        )
            .fallbackToDestructiveMigration()
            .build()



    @Provides
    fun pokemonDao(
        db: PopularPokemonDatabase
    ): PopularPokemonDao = db.pokemonDao()


}