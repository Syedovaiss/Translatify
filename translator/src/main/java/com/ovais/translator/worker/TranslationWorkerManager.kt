package com.ovais.translator.worker

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface TranslationWorkerManager {
    fun enqueue(
        name: String,
        existingWorkPolicy: ExistingWorkPolicy,
        workRequest: OneTimeWorkRequest
    )
}

class DefaultTranslationWorkerManager @Inject constructor(
    @ApplicationContext private val context: Context
) : TranslationWorkerManager {

    private val workManager: WorkManager by lazy {
        WorkManager.getInstance(context)
    }

    override fun enqueue(
        name: String,
        existingWorkPolicy: ExistingWorkPolicy,
        workRequest: OneTimeWorkRequest
    ) {
        workManager.enqueue(
            workRequest
        )
    }
}