package com.nl.professoroak.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.nl.professoroak.R
import com.nl.professoroak.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//    private val navHostFragment by lazy {
//        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}