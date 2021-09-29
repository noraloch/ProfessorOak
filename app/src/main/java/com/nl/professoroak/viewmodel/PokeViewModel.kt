package com.nl.professoroak.viewmodel

import android.app.Application
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nl.professoroak.model.DataWrapper
import com.nl.professoroak.model.request.Queries
import com.nl.professoroak.repo.PokeRepo
import com.nl.professoroak.util.ApiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokeViewModel() : ViewModel() {

    private val _pokeCardsState = MutableLiveData<ApiState<DataWrapper>>()
    val pokeCardsState: LiveData<ApiState<DataWrapper>>
        get() = _pokeCardsState

    var queries: Queries? = null


    fun getImages(queries: Queries?) {
        Log.d(TAG, "getImages queries: $queries")
        viewModelScope.launch {
            PokeRepo.getPokeCardsState(queries).collect { pokeCardsState ->
                _pokeCardsState.postValue(pokeCardsState)
                Log.d(TAG, "getImages pokeCardsState: ${pokeCardsState}")
                Log.d(TAG, "getImages _pokeCardsState: $_pokeCardsState")
            }
        }
    }

    companion object {
        private const val TAG = "PokeViewModel"
    }

}