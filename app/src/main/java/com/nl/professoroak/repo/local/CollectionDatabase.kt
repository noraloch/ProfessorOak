package com.nl.professoroak.repo.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nl.professoroak.model.CardEntity
import com.nl.professoroak.repo.local.dao.CollectionDao
import com.nl.professoroak.repo.local.util.CardConverters

@Database(entities = [CardEntity::class], version = 1)
@TypeConverters(CardConverters::class)
abstract class CollectionDatabase : RoomDatabase() {
    abstract fun collectionDao(): CollectionDao

    companion object {

        private const val DATABASE_NAME = "collection.db"

        // singleton instantiation
        @Volatile
        private var instance: CollectionDatabase? = null

        fun getInstance(context: Context): CollectionDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CollectionDatabase {
            return Room.databaseBuilder(
                context, CollectionDatabase::class.java, DATABASE_NAME
            ).build()
        }
    }

}