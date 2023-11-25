package com.ovais.common.di

import com.ovais.common.ClipboardManager
import com.ovais.common.DefaultClipboardManager
import com.ovais.common.DefaultDispatcherProvider
import com.ovais.common.DefaultFlagResourceProvider
import com.ovais.common.DispatcherProvider
import com.ovais.common.FlagResourceProvider
import com.ovais.common.ads.AdsManager
import com.ovais.common.ads.DefaultAdsManager
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
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

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

    @Singleton
    @Binds
    fun bindStorageManager(
        default: DefaultStorageManager
    ): StorageManager

    @Singleton
    @Binds
    fun bindLocalStorageManager(
        default: DefaultLocalStorageManager
    ): LocalStorageManager

    @Binds
    fun bindDispatcherProvider(
        default: DefaultDispatcherProvider
    ): DispatcherProvider

    @Binds
    fun bindAdsManager(
        default: DefaultAdsManager
    ): AdsManager

    @Binds
    fun bindClipboardManager(
        default: DefaultClipboardManager
    ): ClipboardManager

    @Binds
    fun bindFlagResourceProvider(
        default: DefaultFlagResourceProvider
    ): FlagResourceProvider
}


@Module
@InstallIn(SingletonComponent::class)
class CommonProviderModule {

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            explicitNulls = false
            encodeDefaults = true
        }
    }
}