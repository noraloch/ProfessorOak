package com.nl.professoroak.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokeCard(
    @Json(name = "data")
    val `data`: Data?
)