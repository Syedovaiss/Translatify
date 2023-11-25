package com.ovais.translatify.languages.domain

import com.ovais.common.SuspendUseCase
import com.ovais.translatify.languages.data.LanguageRepository
import com.ovais.translatify.languages.data.LanguagesData
import javax.inject.Inject

interface GetAllLanguagesUseCase : SuspendUseCase<List<LanguagesData>>

class DefaultGetAllLanguagesUseCase @Inject constructor(
    private val languageRepository: LanguageRepository
) : GetAllLanguagesUseCase {

    override suspend fun invoke(): List<LanguagesData> {
        return languageRepository.get()
    }
}