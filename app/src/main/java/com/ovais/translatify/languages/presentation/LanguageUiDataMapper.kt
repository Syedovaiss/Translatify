package com.ovais.translatify.languages.presentation

import com.ovais.common.FlagResourceProvider
import com.ovais.common.utils.default
import com.ovais.translatify.languages.data.LanguagesData
import javax.inject.Inject

interface LanguageUiDataMapper {
    fun map(allLangauges: List<LanguagesData>, installed: List<String>): List<LanguageUiData>
}

class DefaultLanguageUiDataMapper @Inject constructor(
    private val flagResourceProvider: FlagResourceProvider
) : LanguageUiDataMapper {
    override fun map(
        allLangauges: List<LanguagesData>,
        installed: List<String>
    ): List<LanguageUiData> = allLangauges.map { data ->
        LanguageUiData(
            title = data.title.default(),
            code = data.code.default(),
            isInstalled = installed.any { it == data.code },
            flag = flagResourceProvider.get(data.code.default())
        )
    }
}