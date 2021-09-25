package com.nl.professoroak.repo.remote

import com.nl.professoroak.model.Data
import com.nl.professoroak.model.DataWrapper
import com.squareup.moshi.JsonClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface PokeService {

    @GET("v2/cards")
    suspend fun getPokeCards(
        @QueryMap options: Map<String, @JvmSuppressWildcards Any>?
    ): Response<DataWrapper>

}
