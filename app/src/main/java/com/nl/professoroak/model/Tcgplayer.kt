package com.nl.professoroak.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tcgplayer(
    @Json(name = "prices")
    val prices: PricesX?,
    @Json(name = "updatedAt")
    val updatedAt: String?,
    @Json(name = "url")
    val url: String?
)