package com.nl.professoroak.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LegalitiesX(
    @Json(name = "unlimited")
    val unlimited: String?
)