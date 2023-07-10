package com.reydix.assignment.data.network.model

import com.reydix.assignment.data.database.popular_pokemon.entities.PokemonItem
import com.reydix.assignment.data.network.base.BaseResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass



data class PokemonDetailsResponse(

    val name : String,

    val id: Int?,

    val sprites: Sprites?


) : BaseResponse()



data class Sprites(
    val other: PokemonDetailsOther,
)


data class PokemonDetailsOther(
    val home: PokemonDetailsOtherHome
)


data class PokemonDetailsOtherHome(

    @field:Json(name = "front_default")
    val frontDefault: String
)


/**
 * Serializers
 */
fun List<PokemonDetailsResponse?>?.toListPokemonItem() : List<PokemonItem> {
    return this?.mapNotNull {
        PokemonItem(
            it?.id,
            it?.name,
            it?.sprites?.other?.home?.frontDefault
        )
    } ?: listOf()
}