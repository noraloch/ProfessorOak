package com.nl.professoroak.repo

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

        val state = pokeService.getPokeCards(queries?.asQueryMap).getApiState()
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
            nationalPokedexNumbers?.let { "nationalPokedexNumbers" to it },
            q?.let {"q" to it}
        ).toMap()

}