package com.ovais.common.di

import com.ovais.common.DefaultDispatcherProvider
import com.ovais.common.DispatcherProvider
import com.ovais.common.datetime.DateTimeManager
import com.ovais.common.datetime.DefaultDateTimeManager
import com.ovais.common.storage.DefaultLocalStorageManager
import com.ovais.common.storage.DefaultStorageManager
import com.ovais.common.storage.LocalStorageManager
import com.ovais.common.storage.StorageManager
import com.ovais.common.toaster.DefaultToastManager
import com.ovais.common.toaster.ToastManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface CommonModule {

    @Binds
    fun bindToastManager(
        default: DefaultToastManager
    ): ToastManager


    @Binds
    fun bindDateTimeManager(
        default: DefaultDateTimeManager
    ): DateTimeManager

    @Binds
    fun bindStorageManager(
        default: DefaultStorageManager
    ): StorageManager

    @Binds
    fun bindLocalStorageManager(
        default: DefaultLocalStorageManager
    ): LocalStorageManager

    @Binds
    fun bindDispatcherProvider(
        default: DefaultDispatcherProvider
    ): DispatcherProvider
}