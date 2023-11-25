package com.ovais.translatify.home.domain

import com.ovais.translator.tranlation.TranslationManager
import com.ovais.translator.tranlation.TranslationResults
import javax.inject.Inject

interface TranslateContentUseCase {
    suspend operator fun invoke(
        source: String,
        target: String,
        text: String
    ): TranslationResult
}

class DefaultTranslateContentUseCase @Inject constructor(
    private val translationManager: TranslationManager
) : TranslateContentUseCase {

    override suspend fun invoke(
        source: String,
        target: String,
        text: String
    ): TranslationResult {
        return when (
            val result = translationManager.translate(source, target, text)
        ) {
            is TranslationResults.Translated -> {
                TranslationResult.Completed(result.text)
            }

            is TranslationResults.Failed -> {
                TranslationResult.Failed(result.error)
            }
        }
    }
}

sealed interface TranslationResult {
    data class Completed(val text: String) : TranslationResult
    data class Failed(val error: String) : TranslationResult
}