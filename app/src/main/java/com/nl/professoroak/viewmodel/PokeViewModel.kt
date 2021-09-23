package com.nl.professoroak.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nl.professoroak.model.PokeCard
import com.nl.professoroak.util.ApiState

class PokeViewModel(application: Application) : ViewModel() {

    private val _pokeCardsState = MutableLiveData<ApiState<List<PokeCard>>>()
    val pokeCardsState: LiveData<ApiState<List<PokeCard>>>
        get() = _pokeCardsState
}