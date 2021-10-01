package com.nl.professoroak.view

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.nl.professoroak.NavGraphDirections
import com.nl.professoroak.R
import com.nl.professoroak.databinding.ActivityMainBinding
import com.nl.professoroak.util.UserPrefManager
import com.nl.professoroak.viewmodel.PokeViewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val TAG = "Main Activity"
    private lateinit var binding: ActivityMainBinding
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
    private val pokeViewModel: PokeViewModel by viewModels()
    private lateinit var searchView: SearchView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appbar.setupWithNavController(
            navHostFragment.navController
        )

        binding.appbar.setOnMenuItemClickListener { it ->
            when (it.itemId) {
                R.id.myCollectionFragment -> {
                    navHostFragment.navController.navigate(NavGraphDirections.actionGlobalMyCollection())
                    true
                }
                R.id.og_only -> {
                    filterOgOnly()
                    true
                }
                R.id.SHOW_ALL -> {
                    lifecycleScope.launchWhenResumed {
                        UserPrefManager.getInstance(applicationContext).clearQuery()
                        navHostFragment.navController.navigate(NavGraphDirections.actionGlobalCards())
                    }
                    true
                }
                else -> false
            }
        }
        searchView = binding.appbar.findViewById(R.id.search_bar)

        searchView.isSubmitButtonEnabled = true
        searchView.queryHint = "search by name"
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) searchQuery(query)
        searchView.onActionViewCollapsed()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    private fun searchQuery(query: String) {
        val searchQuery = "name:$query*"
        lifecycleScope.launchWhenResumed {
            UserPrefManager.getInstance(applicationContext).saveQuery(searchQuery)
            navHostFragment.navController.navigate(NavGraphDirections.actionGlobalCards())
        }
    }

    private fun filterOgOnly() {
        lifecycleScope.launchWhenResumed {
            UserPrefManager.getInstance(applicationContext).saveQuery("nationalPokedexNumbers:[1 TO 151]")
            navHostFragment.navController.navigate(NavGraphDirections.actionGlobalCards())
        }
    }
}