package com.reydix.assignment.data.database.popular_pokemon.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.reydix.assignment.data.database.base.BaseDaoIdItem
import com.reydix.assignment.ui.home.popular_pokemon.PopularPokemonItem
import kotlinx.parcelize.Parcelize


@Entity(tableName = "pokemon_item_table")
data class PokemonItem(

    @ColumnInfo(name = "pokemon_item_api_item_id")
    var apiItemId: Int? = null,
    //ui fields
    @ColumnInfo(name = "pokemon_item_name")
    var name: String?,
    @ColumnInfo(name = "pokemon_item_img_url")
    var imgUrl: String?,


    @ColumnInfo(name = "pokemon_item_id")
    @PrimaryKey(autoGenerate = true)
    override val id: Long = BaseDaoIdItem.DEFAULT_ID
) :  BaseDaoIdItem


fun List<PokemonItem>.toListPopularPokemonItem(): List<PopularPokemonItem> {
    return mapNotNull {pokemonItem ->
        PopularPokemonItem(pokemonItem.imgUrl, pokemonItem.name)
    }
}