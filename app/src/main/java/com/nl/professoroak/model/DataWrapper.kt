package com.nl.professoroak.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataWrapper(
    val data:List<Data>
)