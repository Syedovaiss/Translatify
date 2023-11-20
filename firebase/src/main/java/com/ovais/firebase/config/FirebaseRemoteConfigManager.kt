package com.ovais.firebase.config

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

interface FirebaseRemoteConfigManager {
    suspend fun activate(completion: () -> Unit)
    fun getString(key: String): String
    fun getBoolean(key: String): Boolean
    fun getLong(key: String): Long
}

class DefaultFirebaseRemoteConfigManager @Inject constructor() : FirebaseRemoteConfigManager {

    private var isActivateCalled: Boolean = false

    private val remoteConfig: FirebaseRemoteConfig by lazy {
        Firebase.remoteConfig
    }

    private val minimumFetchInterval: Long
        get() = 3600

    private val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = minimumFetchInterval
    }

    init {
        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    override suspend fun activate(completion: () -> Unit) {
        val fetch = remoteConfig.fetch(minimumFetchInterval)
        return suspendCancellableCoroutine { continuation ->
            fetch.addOnCompleteListener {
                if (it.isSuccessful) {
                    remoteConfig.activate().addOnCompleteListener { task ->
                        isActivateCalled = task.isSuccessful
                        completion()
                    }
                } else {
                    completion()
                }
            }
        }
    }

    override fun getString(key: String): String {
        return get().getString(key)
    }

    override fun getBoolean(key: String): Boolean {
        return get().getBoolean(key)
    }

    override fun getLong(key: String): Long {
        return get().getLong(key)
    }

    private fun get(): FirebaseRemoteConfig {
        if (isActivateCalled.not()) {
            Log.e("REMOTE_CONFIG", "Remote config not activated")
        }
        return remoteConfig
    }
}