package com.ovais.notification

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
interface NotificationModule {
    @Binds
    fun bindNotificationBuilder(
        default: DefaultTranslatifyNotificationBuilder
    ): TranslatifyNotificationBuilder
    @Binds
    fun bindNotificationManager(
        default: DefaultNotificationsManager
    ): NotificationsManager
}