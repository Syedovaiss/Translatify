package com.ovais.firebase.di

import com.ovais.firebase.config.DefaultFirebaseRemoteConfigManager
import com.ovais.firebase.config.FirebaseRemoteConfigManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface FirebaseModule {

    @Binds
    fun bindRemoteConfigManager(
        default: DefaultFirebaseRemoteConfigManager
    ): FirebaseRemoteConfigManager
}