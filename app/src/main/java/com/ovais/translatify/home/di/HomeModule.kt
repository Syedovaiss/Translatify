package com.ovais.translatify.home.di

import com.ovais.translatify.home.domain.DefaultSupportedLanguageUseCase
import com.ovais.translatify.home.domain.DefaultTranslateContentUseCase
import com.ovais.translatify.home.domain.SupportedLanguageUseCase
import com.ovais.translatify.home.domain.TranslateContentUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
interface HomeModule {

    @Binds
    fun bindSupportedLanguagesUseCase(
        default: DefaultSupportedLanguageUseCase
    ): SupportedLanguageUseCase

    @Binds
    fun bindTranslateContentUseCase(
        default: DefaultTranslateContentUseCase
    ): TranslateContentUseCase
}