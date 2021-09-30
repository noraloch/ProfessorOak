package com.nl.professoroak.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.Preferences
import androidx.appcompat.widget.SearchView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.nl.professoroak.NavGraphDirections
import com.nl.professoroak.R
import com.nl.professoroak.databinding.ActivityMainBinding
import com.nl.professoroak.model.request.Queries
import com.nl.professoroak.util.PreferenceKey
import com.nl.professoroak.viewmodel.PokeViewModel

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

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
        Log.d(TAG, "savedInstanceState: $savedInstanceState")

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
                    pokeViewModel.getImages(Queries(null))
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
            applicationContext.dataStore.edit { settings ->
                settings[PreferenceKey.Q] = searchQuery
            }
        }
        pokeViewModel.getImages(Queries(searchQuery))
    }

    private fun filterOgOnly() {
        pokeViewModel.getImages(Queries("nationalPokedexNumbers:[1 TO 151]"))
        lifecycleScope.launchWhenResumed {
            applicationContext.dataStore.edit { settings ->
                settings[PreferenceKey.Q] = "nationalPokedexNumbers:[1 TO 151]"
            }
        }
    }
}