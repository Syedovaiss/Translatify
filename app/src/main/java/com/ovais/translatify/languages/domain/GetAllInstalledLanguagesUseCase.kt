package com.ovais.translatify.languages.domain

import com.ovais.common.SuspendUseCase
import javax.inject.Inject

interface GetAllInstalledLanguagesUseCase : SuspendUseCase<List<String>>

class DefaultGetAllInstalledLanguagesUseCase @Inject constructor(
    private val languageRepository: DefaultLanguageRepository
) : GetAllInstalledLanguagesUseCase {

    override suspend fun invoke(): List<String> {
        return languageRepository.getInstalledLanguages()
    }
}