package com.ovais.translatify.home.domain

import com.ovais.common.UseCase
import com.ovais.common.parser.ParsingService
import com.ovais.firebase.config.FirebaseRemoteConfigManager
import com.ovais.translatify.home.data.SupportedLanguages
import javax.inject.Inject

interface SupportedLanguageUseCase : UseCase<List<SupportedLanguages>>

class DefaultSupportedLanguageUseCase @Inject constructor(
    private val remoteConfigManager: FirebaseRemoteConfigManager,
    private val parsingService: ParsingService
) : SupportedLanguageUseCase {
    private companion object {
        private const val SUPPORTED_LANGUAGES = "supported_languages"
        private val default = SupportedLanguages(
            code = "en",
            lang = "ENGLISH",
            title = "English"
        )
    }

    override fun invoke(): List<SupportedLanguages> {
        return parsingService.decodeFromString<List<SupportedLanguages>>(
            remoteConfigManager.getString(SUPPORTED_LANGUAGES)
        ) ?: listOf(default)
    }
}