package com.ovais.common.toaster

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface StorageManager {
    suspend fun putString(key: String, value: String)
    suspend fun putBoolean(key: String, value: Boolean)
    suspend fun putLong(key: String, value: Long)
    suspend fun putInt(key: String, value: Int)
    suspend fun putDouble(key: String, value: Double)
    suspend fun putFloat(key: String, value: Float)
    suspend fun getString(key: String): Flow<String>
    suspend fun getFloat(key: String): Flow<Float>
    suspend fun getInt(key: String): Flow<Int>
    suspend fun getDouble(key: String): Flow<Double>
    suspend fun getLong(key: String): Flow<Long>
    suspend fun getBoolean(key: String): Flow<Boolean>
}

class DefaultStorageManager @Inject constructor(
    @ApplicationContext private val context: Context
) : StorageManager {

    private companion object {
        private const val FILE_NAME = "translatify"
    }

    private val Context.appDataStore by preferencesDataStore(
        name = FILE_NAME
    )

    override suspend fun putString(key: String, value: String) {
        context.appDataStore.edit { mutablePreferences ->
            mutablePreferences[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        context.appDataStore.edit { mutablePreferences ->
            mutablePreferences[booleanPreferencesKey(key)] = value
        }
    }

    override suspend fun putLong(key: String, value: Long) {
        context.appDataStore.edit { mutablePreferences ->
            mutablePreferences[longPreferencesKey(key)] = value
        }
    }

    override suspend fun putDouble(key: String, value: Double) {
        context.appDataStore.edit { mutablePreferences ->
            mutablePreferences[doublePreferencesKey(key)] = value
        }
    }

    override suspend fun putFloat(key: String, value: Float) {
        context.appDataStore.edit { mutablePreferences ->
            mutablePreferences[floatPreferencesKey(key)] = value
        }
    }

    override suspend fun putInt(key: String, value: Int) {
        context.appDataStore.edit { mutablePreferences ->
            mutablePreferences[intPreferencesKey(key)] = value
        }
    }

    override suspend fun getBoolean(key: String): Flow<Boolean> =
        context.appDataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(key)]
        }.filterNotNull()

    override suspend fun getDouble(key: String): Flow<Double> =
        context.appDataStore.data.map { preferences ->
            preferences[doublePreferencesKey(key)]
        }.filterNotNull()

    override suspend fun getFloat(key: String): Flow<Float> =
        context.appDataStore.data.map { preferences ->
            preferences[floatPreferencesKey(key)]
        }.filterNotNull()

    override suspend fun getInt(key: String): Flow<Int> =
        context.appDataStore.data.map { preferences ->
            preferences[intPreferencesKey(key)]
        }.filterNotNull()

    override suspend fun getLong(key: String): Flow<Long> =
        context.appDataStore.data.map { preferences ->
            preferences[longPreferencesKey(key)]
        }.filterNotNull()

    override suspend fun getString(key: String): Flow<String> =
        context.appDataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)]
        }.filterNotNull()
}