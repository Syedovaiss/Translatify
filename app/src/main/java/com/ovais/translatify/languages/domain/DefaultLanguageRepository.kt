package com.ovais.translatify.languages.domain

import com.ovais.common.dtos.QueryResult
import com.ovais.common.parser.ParsingService
import com.ovais.firebase.config.FirebaseRemoteConfigManager
import com.ovais.translatify.languages.data.LanguageRepository
import com.ovais.translatify.languages.data.LanguagesData
import com.ovais.translator.tranlation.TranslationModelManager
import javax.inject.Inject

class DefaultLanguageRepository @Inject constructor(
    private val remoteConfigManager: FirebaseRemoteConfigManager,
    private val translationModelManager: TranslationModelManager,
    private val parsingService: ParsingService
) : LanguageRepository {
    private companion object {
        private const val KEY_ALL_LANGUAGES = "supported_languages"
    }


    override suspend fun get(): List<LanguagesData> {
        return parsingService.decodeFromString<List<LanguagesData>>(
            remoteConfigManager.getString(KEY_ALL_LANGUAGES)
        ) ?: listOf(LanguagesData.default)
    }

    override suspend fun getInstalledLanguages(): List<String> {
        return when (val installed = translationModelManager.get()) {
            is QueryResult.Success -> {
                installed.data.map { it.language }
            }

            is QueryResult.Failed -> emptyList()
        }
    }
}