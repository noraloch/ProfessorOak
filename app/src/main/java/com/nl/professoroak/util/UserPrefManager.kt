package com.nl.professoroak.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.nl.professoroak.model.request.Queries
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")
class UserPrefManager private constructor(private val dataStore: DataStore<Preferences>) {

    val queries
        get() = dataStore.data.map { preferences ->
            preferences[PreferenceKey.Q]?.let {
                Queries(
                    q = preferences[PreferenceKey.Q]
                )
            }
        }

    suspend fun saveQuery(query: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.Q] = query
        }

    }

    suspend fun clearQuery() {
        dataStore.edit {
            it.clear()
        }
    }

    companion object {
        private var INSTANCE: UserPrefManager? = null

        fun getInstance(context: Context): UserPrefManager {
            if (INSTANCE == null) INSTANCE = UserPrefManager(context.dataStore)
            return INSTANCE!!

        }

    }

}