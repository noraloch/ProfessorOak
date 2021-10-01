package com.nl.professoroak.repo.local.util

import androidx.room.TypeConverter
import com.nl.professoroak.model.Images
import com.nl.professoroak.model.Weaknesse
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type


class CardConverters {

    private val moshi by lazy { Moshi.Builder().build() }

    private val weaknesseListAdapter by lazy {
        val type: Type = Types.newParameterizedType(List::class.java, Weaknesse::class.java)
        return@lazy moshi.adapter<List<Weaknesse>>(type)
    }

    private val nationalPokedexNumberAdapter by lazy {
        val type: Type = Types.newParameterizedType(List::class.java, Int::class.java)
        return@lazy moshi.adapter<List<Int>>(type)
    }

    private val imagesAdapter by lazy {
        return@lazy moshi.adapter(Images::class.java)
    }


    @TypeConverter
    fun stringToWeaknesses(data: String?): List<Weaknesse> {
        return data?.let { weaknesseListAdapter.fromJson(it) } ?: emptyList()
    }

    @TypeConverter
    fun weaknessesToString(someObjects: List<Weaknesse>): String {
        return weaknesseListAdapter.toJson(someObjects)
    }

    @TypeConverter
    fun stringToNationalPokedexNumber(data: String?): List<Int> {
        return data?.let { nationalPokedexNumberAdapter.fromJson(it) } ?: emptyList()
    }

    @TypeConverter
    fun nationalPokedexNumberToString(someObjects: List<Int> ): String {
        return nationalPokedexNumberAdapter.toJson(someObjects)
    }

    @TypeConverter
    fun stringToImages(data: String) : Images? {
        return data.let { imagesAdapter.fromJson(it) }
    }

    @TypeConverter
    fun imagesToString(someObjects: Images ): String {
        return imagesAdapter.toJson(someObjects)
    }



}