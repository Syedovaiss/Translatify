package com.ovais.translator.worker

import android.app.Notification
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ForegroundInfo
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.ovais.common.dtos.QueryResult
import com.ovais.notification.NotificationsManager
import com.ovais.translator.tranlation.TranslationModelManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.withContext
import java.util.UUID


@HiltWorker
class ModelDownloadWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val notificationManager: NotificationsManager,
    private val translationModelManager: TranslationModelManager
) : CoroutineWorker(context, params) {

    companion object {
        private const val TAG = "tag:ModelDownloadWorker"
        private const val MODEL_NAME = "translation_model"

        fun buildWorker(
            model: String
        ) = OneTimeWorkRequestBuilder<ModelDownloadWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .setInputData(
                Data
                    .Builder()
                    .putString(MODEL_NAME, model)
                    .build()
            )
            .setId(UUID.randomUUID())
            .addTag(TAG)
            .build()
    }

    private val progressNotificationTitle: String
        get() = "Downloading"

    private val progressNotificationDescription: String
        get() = "Downloading requested model"

    override suspend fun doWork(): Result {
        setForeground(getForegroundInfo())
        return inputData.getString(MODEL_NAME)?.let { model ->
            try {
                withContext(limitedCoroutineContext) {
                    when (val result = translationModelManager.download(model)) {
                        is QueryResult.Success -> {
                            Result.Success(workDataOf())
                        }

                        is QueryResult.Failed -> {
                            createErrorNotification(result.message)
                            Result.Failure(
                                workDataOf(ERROR_KEY to result.message)
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                Result.failure(workDataOf(ERROR_KEY to e.message))
            }
        } ?: Result.failure(workDataOf(ERROR_KEY to "invalid model provided!"))

    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return createForegroundInfo(
            notificationManager.getNotification(
                progressNotificationTitle,
                progressNotificationDescription
            )
        )
    }

    private fun createForegroundInfo(notification: Notification): ForegroundInfo {
        return ForegroundInfo(notificationManager.notificationId, notification)
    }

    private fun createErrorNotification(error: String) {
        notificationManager.notify("Failed to download model", error)
    }
}