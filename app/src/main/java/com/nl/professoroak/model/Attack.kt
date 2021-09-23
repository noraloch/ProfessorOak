package com.nl.professoroak.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Attack(
    @Json(name = "convertedEnergyCost")
    val convertedEnergyCost: Int?,
    @Json(name = "cost")
    val cost: List<String>?,
    @Json(name = "damage")
    val damage: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "text")
    val text: String?
)