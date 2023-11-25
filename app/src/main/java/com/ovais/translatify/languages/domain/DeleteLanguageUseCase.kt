package com.ovais.translatify.languages.domain

import com.ovais.common.ParamUseCase
import com.ovais.translator.tranlation.TranslationManager
import javax.inject.Inject

interface DeleteLanguageUseCase : ParamUseCase<String, Unit>

class DefaultDeleteLanguageUseCase @Inject constructor(
    private val translationManager: TranslationManager
) : DeleteLanguageUseCase {

    override fun invoke(param: String) {
        translationManager.download(param)
    }
}