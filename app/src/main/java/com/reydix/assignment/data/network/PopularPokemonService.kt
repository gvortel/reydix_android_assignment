package com.reydix.assignment.data.network

import com.reydix.assignment.data.network.model.PokemonDetailsResponse
import com.reydix.assignment.data.network.model.PopularPokemonResponse
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PopularPokemonService {

    @GET("pokemon")
    fun getPopularPokemon(): Call<PopularPokemonResponse>

    @GET("pokemon/{pokemonId}")
    fun getPokemonDetails(
        @Path("pokemonId") pokemonId: String,
    ): Call<PokemonDetailsResponse>
}