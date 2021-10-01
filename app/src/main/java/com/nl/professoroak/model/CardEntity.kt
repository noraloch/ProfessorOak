package com.nl.professoroak.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "collection_table")
@JsonClass(generateAdapter = true)
data class CardEntity(
    val hp: String?,
    @PrimaryKey val id: String,
    val images: Images?,
    val name: String?,
    val nationalPokedexNumbers: List<Int>?,
    val number: String?,
    val rarity: String?,
    val weaknesses: List<Weaknesse>?
)