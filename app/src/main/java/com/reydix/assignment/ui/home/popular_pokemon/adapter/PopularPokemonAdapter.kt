package com.reydix.assignment.ui.home.popular_pokemon.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.reydix.assignment.R
import com.reydix.assignment.common.base.BaseAdapter
import com.reydix.assignment.ui.home.popular_pokemon.PopularPokemonItem

class PopularPokemonAdapter (items: List<PopularPokemonItem>)
    : BaseAdapter<PopularPokemonAdapter.PopularPokemonHolder>(items) {

    override val layoutId: Int = R.layout.item_popular_pokemon

    override fun onBindData(holder: PopularPokemonHolder, item: Any) {
        (item as? PopularPokemonItem)?.run { holder.bind(this) }
    }

    override fun createViewHolder(view: View): PopularPokemonHolder {
        return PopularPokemonHolder(view)
    }

    inner class PopularPokemonHolder(itemView: View) : BaseViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.item_popular_pokemon_img)
        private val titleView: TextView = itemView.findViewById(R.id.item_popular_pokemon_title)

        fun bind(item: PopularPokemonItem) {
            titleView.text = item.title

            Glide.with(imageView.context)
                .load(item.imageURL)
                .apply(RequestOptions.circleCropTransform())
                .into(imageView)

        }
    }
}