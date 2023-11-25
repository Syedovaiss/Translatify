package com.ovais.translatify.languages.di

import com.ovais.translatify.languages.data.LanguageRepository
import com.ovais.translatify.languages.domain.DefaultDeleteLanguageUseCase
import com.ovais.translatify.languages.domain.DefaultDownloadLanguageUseCase
import com.ovais.translatify.languages.domain.DefaultGetAllInstalledLanguagesUseCase
import com.ovais.translatify.languages.domain.DefaultGetAllLanguagesUseCase
import com.ovais.translatify.languages.domain.DefaultLanguageRepository
import com.ovais.translatify.languages.domain.DeleteLanguageUseCase
import com.ovais.translatify.languages.domain.DownloadLanguageUseCase
import com.ovais.translatify.languages.domain.GetAllInstalledLanguagesUseCase
import com.ovais.translatify.languages.domain.GetAllLanguagesUseCase
import com.ovais.translatify.languages.presentation.DefaultLanguageUiDataMapper
import com.ovais.translatify.languages.presentation.LanguageUiDataMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
interface LanguageModule {
    @Binds
    fun bindLanguageRepository(
        default: DefaultLanguageRepository
    ): LanguageRepository

    @Binds
    fun bindAllLanguagesUseCase(
        default: DefaultGetAllLanguagesUseCase
    ): GetAllLanguagesUseCase

    @Binds
    fun bindGetInstalledLanguageUseCase(
        default: DefaultGetAllInstalledLanguagesUseCase
    ): GetAllInstalledLanguagesUseCase

    @Binds
    fun bindLanguageUiDataMapper(
        default: DefaultLanguageUiDataMapper
    ): LanguageUiDataMapper

    @Binds
    fun bindDeleteLanguageUseCase(
        default: DefaultDeleteLanguageUseCase
    ): DeleteLanguageUseCase

    @Binds
    fun bindDownloadLanguageUseCase(
        default: DefaultDownloadLanguageUseCase
    ): DownloadLanguageUseCase
}