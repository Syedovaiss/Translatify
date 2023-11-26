package com.ovais.translatify.languages.domain

import com.ovais.common.SuspendParamUseCase
import com.ovais.common.dtos.QueryResult
import com.ovais.translator.tranlation.TranslationManager
import javax.inject.Inject

interface DownloadLanguageUseCase : SuspendParamUseCase<String, DownloadResults>

class DefaultDownloadLanguageUseCase @Inject constructor(
    private val translationManager: TranslationManager
) : DownloadLanguageUseCase {

    override suspend fun invoke(param: String): DownloadResults {
        return when (val result = translationManager.download(param)) {
            is QueryResult.Success -> DownloadResults.Downloaded
            is QueryResult.Failed -> DownloadResults.Failed(result.message)
        }
    }
}

sealed interface DownloadResults {
    object Downloaded : DownloadResults
    data class Failed(val error: String) : DownloadResults
}