package com.nl.professoroak.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val artist: String?,
    val cardmarket: Cardmarket?,
    val hp: String?,
    val id: String?,
    val images: Images?,
    val name: String?,
    val nationalPokedexNumbers: List<Int>?,
    val number: String?,
    val rarity: String?,
    val set: Set?,
    val subtypes: List<String>?,
    val supertype: String?,
    val tcgplayer: Tcgplayer?,
    val types: List<String>?,
    val weaknesses: List<Weaknesse>?
)

