package com.ovais.notification

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


interface NotificationsManager {
    val notificationId: Int
    fun onNotificationReceived(message: RemoteMessage)
    fun onTokenUpdated(token: String)
    fun notify(title: String, description: String, progress: Int, isSilent: Boolean = false)
    fun notify(title: String, description: String, isSilent: Boolean = false)
    fun getNotification(title: String, description: String, isSilent: Boolean = false): Notification
}

class DefaultNotificationsManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val notificationBuilder: TranslatifyNotificationBuilder
) : NotificationsManager {

    private val notificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override val notificationId: Int by lazy {
        (0..5000).random()
    }

    override fun onNotificationReceived(message: RemoteMessage) {
        val notificationId: Int = (0..500).random()
        val notification = notificationBuilder.buildProgressNotification(message)
        notificationManager.notify(notificationId, notification)
    }

    override fun onTokenUpdated(token: String) {
//        liveBazaarLogger.info("New Token Received:$token")
//        localStorageManager.saveFCMToken(token)
    }

    override fun notify(
        title: String,
        description: String,
        progress: Int,
        isSilent: Boolean
    ) {
        notificationManager.notify(
            notificationId,
            notificationBuilder.buildProgressNotification(title, description, progress, isSilent)
        )
    }

    override fun notify(title: String, description: String, isSilent: Boolean) {
        notificationManager.notify(
            notificationId,
            notificationBuilder.buildNotification(title, description, isSilent)
        )
    }

    override fun getNotification(
        title: String,
        description: String,
        isSilent: Boolean
    ): Notification {
        return notificationBuilder.buildNotification(title, description, isSilent)
    }

}