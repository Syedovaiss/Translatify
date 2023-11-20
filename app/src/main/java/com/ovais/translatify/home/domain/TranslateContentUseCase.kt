package com.ovais.translatify.home.domain

import com.ovais.common.translation.TranslationManager
import javax.inject.Inject

interface TranslateContentUseCase {
    operator fun invoke(
        source: String,
        target: String,
        text: String,
        onCompletion: (String) -> Unit
    ): String
}

class DefaultTranslateContentUseCase @Inject constructor(
    private val translationManager: TranslationManager
) : TranslateContentUseCase {

    override fun invoke(
        source: String,
        target: String,
        text: String,
        onCompletion: (String) -> Unit
    ): String {
        translationManager.translate(source, target, text) {
            onCompletion(it)
        }
        return ""
    }
}