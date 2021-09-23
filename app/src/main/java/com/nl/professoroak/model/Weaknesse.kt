package com.nl.professoroak.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weaknesse(
    @Json(name = "type")
    val type: String?,
    @Json(name = "value")
    val value: String?
)