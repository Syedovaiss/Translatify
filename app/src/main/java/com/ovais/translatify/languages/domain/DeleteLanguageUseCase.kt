package com.ovais.translatify.languages.domain

import com.ovais.common.SuspendParamUseCase
import com.ovais.common.dtos.QueryResult
import com.ovais.translator.tranlation.TranslationManager
import javax.inject.Inject

interface DeleteLanguageUseCase : SuspendParamUseCase<String, DeletionResult>

class DefaultDeleteLanguageUseCase @Inject constructor(
    private val translationManager: TranslationManager
) : DeleteLanguageUseCase {

    override suspend fun invoke(param: String): DeletionResult {
        return when (val result = translationManager.delete(param)) {
            is QueryResult.Success -> DeletionResult.Deleted
            is QueryResult.Failed -> DeletionResult.Failed(result.message)
        }
    }
}

sealed interface DeletionResult {
    object Deleted : DeletionResult
    data class Failed(val error: String) : DeletionResult
}