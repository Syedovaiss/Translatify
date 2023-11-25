package com.ovais.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.RemoteMessage
import com.ovais.common.utils.EMPTY_STRING
import com.ovais.common.utils.default
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


interface TranslatifyNotificationBuilder {

    fun buildProgressNotification(
        message: RemoteMessage
    ): Notification

    fun buildPictureNotification(
        message: RemoteMessage,
        imageBitmap: Bitmap?
    ): Notification

    fun buildProgressNotification(
        title: String,
        description: String,
        progress: Int,
        isSilent: Boolean
    ): Notification

    fun buildNotification(
        title: String,
        description: String,
        isSilent: Boolean
    ): Notification
}

class DefaultTranslatifyNotificationBuilder @Inject constructor(
    @ApplicationContext private val context: Context
) : TranslatifyNotificationBuilder {

    private companion object {
        private const val KEY_TITLE = "title"
        private const val KEY_BODY = "body"
        private const val KEY_DEEP_LINK = "deepLink"
        private const val ARGS_DATA = "args_data"
        private const val ACTIVITY = "com.ovais.translatify.app" +
                ".activity.presentation.TranslatifyActivity"
    }

    private val pendingIntentFlags by lazy {
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    }

    private val soundUri by lazy {
        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    }

    private val importance by lazy {
        NotificationManagerCompat.IMPORTANCE_HIGH
    }

    private val notificationChannel: String
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(notificationChannelId, appName, importance).apply {
                description = appName
            }
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            channel.setSound(soundUri, audioAttributes)
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            channel.id
        } else EMPTY_STRING

    private val vibrationPattern by lazy {
        longArrayOf(
            1000, 1000, 1000,
            1000, 1000
        )
    }

    private val appName by lazy {
        context.resources.getString(com.ovais.common.R.string.app_name)
    }

    private val notificationChannelId: String
        get() = (0..500).random().toString()

    override fun buildProgressNotification(
        message: RemoteMessage
    ): Notification {
        return getTextNotificationBuilder(message).build()
    }

    override fun buildProgressNotification(
        title: String,
        description: String,
        progress: Int,
        isSilent: Boolean
    ): Notification {
        return getTextNotificationBuilder(title, description)
            .setProgress(100, progress, false)
            .setSilent(isSilent)
            .build()
    }

    override fun buildPictureNotification(
        message: RemoteMessage,
        imageBitmap: Bitmap?
    ): Notification {
        return getImageNotificationBuilder(message, imageBitmap).build()
    }

    override fun buildNotification(
        title: String,
        description: String,
        isSilent: Boolean
    ): Notification {
        return getTextNotificationBuilder(title, description)
            .setSilent(isSilent)
            .build()
    }

    private fun getImageNotificationBuilder(
        message: RemoteMessage,
        bitmap: Bitmap?
    ): NotificationCompat.Builder {
        val title = message.data[KEY_TITLE]?.let {
            it.default()
        } ?: message.notification?.title.default()
        val body = message.data[KEY_BODY]?.let {
            it.default()
        } ?: message.notification?.body.default()
        return NotificationCompat.Builder(context, notificationChannel).apply {
            val activity = Class.forName(ACTIVITY)
            val intent = Intent(context, activity).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            intent.apply {
                putExtra(ARGS_DATA, message.data[KEY_DEEP_LINK])
            }
            setSmallIcon(com.ovais.common.R.drawable.ic_info)
            setContentTitle(title)
            setAutoCancel(true)
            setSound(soundUri)
            setContentText(body)
            setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
            setVibrate(vibrationPattern)
            priority = NotificationCompat.PRIORITY_DEFAULT
            val requestCode = (0..1000).random()
            setContentIntent(
                PendingIntent.getActivity(
                    context, requestCode, intent, pendingIntentFlags
                )
            )
        }
    }

    private fun getTextNotificationBuilder(message: RemoteMessage): NotificationCompat.Builder {
        val title = message.data[KEY_TITLE]?.let {
            it.default()
        } ?: message.notification?.title.default()
        val body = message.data[KEY_BODY]?.let {
            it.default()
        } ?: message.notification?.body.default()
        return getTextNotificationBuilder(
            argsData = message.data[KEY_DEEP_LINK],
            title = title,
            description = body
        )
    }

    private fun getTextNotificationBuilder(
        title: String,
        description: String,
        argsData: String? = null
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, notificationChannel).apply {
            val activity = Class.forName(ACTIVITY)
            val intent = Intent(context, activity).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            argsData?.let {
                intent.apply {
                    putExtra(ARGS_DATA, it)
                }
            }
            setSmallIcon(com.ovais.common.R.drawable.ic_info)
            setContentTitle(title)
            setAutoCancel(true)
            setSound(soundUri)
            setContentText(description)
            setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(description)
            )
            setVibrate(vibrationPattern)
            priority = NotificationCompat.PRIORITY_DEFAULT
            val requestCode = (0..1000).random()
            setContentIntent(
                PendingIntent.getActivity(
                    context, requestCode, intent, pendingIntentFlags
                )
            )
        }
    }
}