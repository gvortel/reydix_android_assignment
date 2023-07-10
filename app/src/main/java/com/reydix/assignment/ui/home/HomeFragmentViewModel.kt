package com.reydix.assignment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.reydix.assignment.R
import com.reydix.assignment.data.network.base.ResponseListener
import com.reydix.assignment.data.network.base.ResponseListenerIsAttached
import com.reydix.assignment.data.database.popular_pokemon.entities.PokemonItem
import com.reydix.assignment.data.repository.PopularPokemonGetRepository
import com.reydix.assignment.ui.home.popular_pokemon.adapter.PopularPokemonAdapter
import com.reydix.assignment.ui.home.week_events.WeekEventItem
import com.reydix.assignment.ui.home.week_events.adapter.WeekEventsAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val popularPokemonGetRepository: PopularPokemonGetRepository
) : ViewModel() {

    val weekEventsAdapter by lazy { WeekEventsAdapter(getWeekEventsData()) }
    val popularPokemonAdapter by lazy { PopularPokemonAdapter(listOf()) }

    /**
     * Repository
     */
    fun fetchPopularPokemonFromRepo(
        callback: ResponseListener<Any?>,
        responseListenerIsAttached: ResponseListenerIsAttached
    ): LiveData<List<PokemonItem>> {
        return popularPokemonGetRepository.getPopularPokemon(
            callback = callback,
            forceApi = true,
            responseListenerIsAttached = responseListenerIsAttached
        )
    }


    /**
     * Logic
     */

    private fun getWeekEventsData(): List<WeekEventItem> {
        val mutableList = mutableListOf<WeekEventItem>()
        for (i in 1..20) {
            mutableList.add(
                WeekEventItem(R.drawable.week_event_1,
                    "Legend? Go! Friends? Go!",
                    "Sun 18 Oct",
                    "Cinnabar Island")
            )
        }
        return mutableList
    }
}