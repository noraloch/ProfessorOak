package com.nl.professoroak.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.nl.professoroak.NavGraphDirections
import com.nl.professoroak.R
import com.nl.professoroak.databinding.ActivityMainBinding
import com.nl.professoroak.model.request.Queries
import com.nl.professoroak.viewmodel.PokeViewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val TAG = "Main Activity"
    private lateinit var binding: ActivityMainBinding
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
    private val pokeViewModel: PokeViewModel by viewModels()

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
                    // TODO: 9/28/21 Refetching with different endpoint 151 og
                    navHostFragment.navController.navigate(NavGraphDirections.actionGlobalCards())
                    true
                }
                else -> false
            }
        }

        val searchView = binding.appbar.findViewById<SearchView>(R.id.search_bar)

        searchView.isSubmitButtonEnabled = true
        searchView.queryHint = "search..."
        searchView.setOnQueryTextListener(this)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
//        Toast.makeText(this, "submit", Toast.LENGTH_LONG).show()
        if (query != null) searchQuery(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
//        Toast.makeText(this, "typing", Toast.LENGTH_LONG).show()
        return false
    }

    private fun searchQuery(query: String) {
        val searchQuery = "name:$query*"
        // TODO: 9/28/21 get the first argument
        pokeViewModel.getImages(Queries(null, searchQuery))
    }

}