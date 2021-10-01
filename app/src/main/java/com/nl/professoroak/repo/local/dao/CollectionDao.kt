package com.nl.professoroak.repo.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nl.professoroak.model.CardEntity

@Dao
interface CollectionDao {
    @Query("SELECT * FROM collection_table")
    fun getAll(): List<CardEntity>

    @Insert
    fun insertCard(card: CardEntity)

    @Delete
    fun delete(card: CardEntity)
}