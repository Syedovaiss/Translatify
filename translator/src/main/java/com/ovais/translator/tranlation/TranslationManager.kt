package com.ovais.translator.tranlation

import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.ovais.common.dtos.QueryResult
import javax.inject.Inject

interface TranslationManager {
    suspend fun translate(
        source: String,
        target: String,
        text: String
    ): TranslationResults

    suspend fun download(model: String): QueryResult<Void>
    suspend fun delete(model: String): QueryResult<Void>
}

class DefaultTranslationManager @Inject constructor(
    private val translationModelManager: TranslationModelManager
) : TranslationManager {

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

    override suspend fun download(model: String): QueryResult<Void> {
        return translationModelManager.download(model)
    }

    override suspend fun delete(model: String): QueryResult<Void> {
        return translationModelManager.delete(model)
    }
}