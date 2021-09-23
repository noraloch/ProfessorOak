package com.nl.professoroak.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cardmarket(
    @Json(name = "prices")
    val prices: Prices?,
    @Json(name = "updatedAt")
    val updatedAt: String?,
    @Json(name = "url")
    val url: String?
)