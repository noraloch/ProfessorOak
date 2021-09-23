package com.nl.professoroak.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "artist")
    val artist: String?,
    @Json(name = "attacks")
    val attacks: List<Attack>?,
    @Json(name = "cardmarket")
    val cardmarket: Cardmarket?,
    @Json(name = "evolvesFrom")
    val evolvesFrom: String?,
    @Json(name = "hp")
    val hp: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "images")
    val images: Images?,
    @Json(name = "legalities")
    val legalities: Legalities?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "nationalPokedexNumbers")
    val nationalPokedexNumbers: List<Int>?,
    @Json(name = "number")
    val number: String?,
    @Json(name = "rarity")
    val rarity: String?,
    @Json(name = "rules")
    val rules: List<String>?,
    @Json(name = "set")
    val `set`: Set?,
    @Json(name = "subtypes")
    val subtypes: List<String>?,
    @Json(name = "supertype")
    val supertype: String?,
    @Json(name = "tcgplayer")
    val tcgplayer: Tcgplayer?,
    @Json(name = "types")
    val types: List<String>?,
    @Json(name = "weaknesses")
    val weaknesses: List<Weaknesse>?
)