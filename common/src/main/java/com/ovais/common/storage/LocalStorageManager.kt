package com.ovais.common.storage

import com.ovais.common.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LocalStorageManager {
    suspend fun isLoggedIn(): Flow<Boolean?>
}

class DefaultLocalStorageManager @Inject constructor(
    private val storageManager: StorageManager,
    private val dispatcherProvider: DispatcherProvider
) : LocalStorageManager {

    private val coroutineScope by lazy {
        CoroutineScope(dispatcherProvider.background)
    }

    private companion object {
        private const val KEY_IS_LOGGED_IN = "IS_LOGGED_IN"
    }

    override suspend fun isLoggedIn(): Flow<Boolean?> {
        return storageManager.getBoolean(KEY_IS_LOGGED_IN)
    }
}