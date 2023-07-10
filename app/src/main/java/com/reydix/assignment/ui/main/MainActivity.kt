package com.reydix.assignment.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.reydix.assignment.R
import com.reydix.assignment.ui.home.HomeFragment
import com.reydix.assignment.ui.search.SearchFragment
import com.reydix.assignment.ui.tickets.TicketsFragment

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private val viewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init bottom nav bar
        initBottomNavigation()

        // Set the initial fragment
        replaceFragment(HomeFragment())
    }

    private fun initBottomNavigation() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.activity_main_navigation)
        bottomNavigationView.setOnItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.navigation_item_1 -> {
                replaceFragment(HomeFragment())
                return true
            }

            R.id.navigation_item_2 -> {
                replaceFragment(SearchFragment())
                return true
            }

            R.id.navigation_item_3 -> {
                replaceFragment(TicketsFragment())
                return true
            }
        }
        return false

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_container, fragment)
            .commit()
    }
}