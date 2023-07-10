package com.reydix.assignment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reydix.assignment.R
import com.reydix.assignment.common.base.CustomSnapHelper
import com.reydix.assignment.data.network.base.ResponseListener
import com.reydix.assignment.data.network.base.ResponseListenerIsAttached
import com.reydix.assignment.data.database.popular_pokemon.entities.PokemonItem
import com.reydix.assignment.data.database.popular_pokemon.entities.toListPopularPokemonItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), ResponseListenerIsAttached {

    override fun isAttached(): Boolean = isResumed

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  inflater.inflate(R.layout.fragment_home, container, false)

        initWeekEventsRecyclerView(v)
        initPopularRecyclerView(v)
        observeLiveData()

        return v
    }

    override fun onResume() {
        super.onResume()
        popularPokemonLiveData
    }


    /**
     * Observe Popular Pokemon repository
     */
    private val fetchPopularPokemonCallback = object : ResponseListener<Any?>() {
        override fun success(t: Any?) {
            super.success(t)
            //TODO
        }

        override fun error(error: String?) {
            super.error(error)
            //TODO
        }
    }

    private val popularPokemonLiveData: LiveData<List<PokemonItem>>
        get() =
            viewModel.fetchPopularPokemonFromRepo(
                fetchPopularPokemonCallback,
                this@HomeFragment
            )

    private fun observeLiveData(){
        popularPokemonLiveData.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.popularPokemonAdapter.updateItems(
                    it.toListPopularPokemonItem()
                )
            }
        }
    }



    /**
     * Init Views
     */
    private fun initWeekEventsRecyclerView(v: View) {
        val weekEventsRecyclerView = v.findViewById<RecyclerView>(R.id.fragment_home_events_recyclerview)
        weekEventsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        weekEventsRecyclerView.adapter = viewModel.weekEventsAdapter
    }

    private fun initPopularRecyclerView(v: View) {
        val weekEventsRecyclerView = v.findViewById<RecyclerView>(R.id.fragment_home_popular_recyclerview)
        weekEventsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        weekEventsRecyclerView.adapter = viewModel.popularPokemonAdapter

        // add carousel effect
        val snapHelper = CustomSnapHelper()
        snapHelper.attachToRecyclerView(weekEventsRecyclerView)
    }
}