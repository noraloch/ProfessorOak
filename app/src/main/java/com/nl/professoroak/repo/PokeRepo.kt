package com.nl.professoroak.repo

import android.util.Log
import com.nl.professoroak.model.request.Queries
import com.nl.professoroak.repo.remote.RetrofitInstance
import com.nl.professoroak.util.ApiState
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object PokeRepo {
    private const val TAG = "POKE-REPO"
    private val pokeService by lazy { RetrofitInstance.pokeService }

    fun getPokeCardsState(queries: Queries?) = flow {
        emit(ApiState.Loading)
        Log.d(TAG, "getPokeCardsState 66: $queries")
        val state = pokeService.getPokeCards(queries?.asQueryMap).getApiState()
        Log.d(TAG, "getPokeCardsState 2: $state")
        emit(state)
    }

    private fun <T>  Response<T>.getApiState(): ApiState<T> {
        return if (isSuccessful) {
            if (body()== null) ApiState.EndOfPage
            else ApiState.Success(body()!!)
        } else ApiState.Failure("Error fetching data.")
    }

    private val Queries.asQueryMap: Map<String, Any>
        get() = listOfNotNull(
            q?.let {"q" to it}
        ).toMap()

}