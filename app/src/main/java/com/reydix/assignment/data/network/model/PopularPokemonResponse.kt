package com.reydix.assignment.data.network.model

import com.reydix.assignment.data.network.base.BaseResponse


data class PopularPokemonResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,

    val results: List<PopularPokemonResponseItem>
) : BaseResponse()


data class PopularPokemonResponseItem(
    val name: String?,
    val url: String?
)