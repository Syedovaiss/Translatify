package com.ovais.translator.tranlation

import androidx.work.ExistingWorkPolicy
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.ovais.common.dtos.QueryResult
import com.ovais.translator.worker.ModelDeleteWorker
import com.ovais.translator.worker.ModelDownloadWorker
import com.ovais.translator.worker.TranslationWorkerManager
import javax.inject.Inject

interface TranslationManager {
    suspend fun translate(
        source: String,
        target: String,
        text: String
    ): TranslationResults

    fun download(model: String)
    fun delete(model: String)
}

class DefaultTranslationManager @Inject constructor(
    private val translationWorkerManager: TranslationWorkerManager,
    private val translationModelManager: TranslationModelManager
) : TranslationManager {

    private companion object {
        private const val MODEL_DOWNLOAD_WORK_NAME = "MODEL_DOWNLOAD_WORK_NAME"
        private const val MODEL_DELETE_WORK_NAME = "MODEL_DELETE_WORK_NAME"
    }

    override suspend fun translate(
        source: String,
        target: String,
        text: String
    ): TranslationResults {
        return when (val models = translationModelManager.get()) {
            is QueryResult.Success -> {
                val isSourceExists = models.data.any { it.language == source }
                val isTargetExists = models.data.any { it.language == target }
                if (isSourceExists.not()) {
                    return TranslationResults.Failed(
                        "$source language not installed on device." +
                                "Please download it from settings."
                    )
                }
                if (isTargetExists.not()) {
                    return TranslationResults.Failed(
                        "$target language not installed on device." +
                                "Please download it from settings."
                    )
                } else {
                    when (val results = getTranslatedText(source, target, text)) {
                        is QueryResult.Success -> TranslationResults.Translated(results.data)
                        is QueryResult.Failed -> TranslationResults.Failed(results.message)
                    }
                }
            }

            is QueryResult.Failed -> {
                TranslationResults.Failed("Something goes wrong on our side. We are checking")
            }
        }
    }

    private suspend fun getTranslatedText(
        source: String,
        target: String,
        text: String
    ): QueryResult<String> {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(source)
            .setTargetLanguage(target)
            .build()
        val translator = Translation.getClient(options)
        return translator.translate(text).completeAsQueryResult()
    }

    override fun download(model: String) {
        val workRequest = ModelDownloadWorker.buildWorker(model)
        translationWorkerManager.enqueue(
            name = MODEL_DOWNLOAD_WORK_NAME,
            existingWorkPolicy = ExistingWorkPolicy.APPEND_OR_REPLACE,
            workRequest = workRequest
        )
    }

    override fun delete(model: String) {
        val workRequest = ModelDeleteWorker.buildWorker(model)
        translationWorkerManager.enqueue(
            name = MODEL_DELETE_WORK_NAME,
            existingWorkPolicy = ExistingWorkPolicy.APPEND_OR_REPLACE,
            workRequest = workRequest
        )
    }
}