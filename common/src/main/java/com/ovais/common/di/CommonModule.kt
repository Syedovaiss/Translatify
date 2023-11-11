package com.ovais.common.di

import com.ovais.common.datetime.DateTimeManager
import com.ovais.common.datetime.DefaultDateTimeManager
import com.ovais.common.toaster.DefaultStorageManager
import com.ovais.common.toaster.DefaultToastManager
import com.ovais.common.toaster.StorageManager
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
}