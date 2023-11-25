package com.ovais.translator.di

import com.ovais.translator.tranlation.DefaultTranslationManager
import com.ovais.translator.tranlation.DefaultTranslationModelManager
import com.ovais.translator.tranlation.TranslationManager
import com.ovais.translator.tranlation.TranslationModelManager
import com.ovais.translator.worker.DefaultTranslationWorkerManager
import com.ovais.translator.worker.TranslationWorkerManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
interface TranslatorModule {

    @Binds
    fun bindTranslationManager(
        default: DefaultTranslationManager
    ): TranslationManager

    @Binds
    fun bindTranslationModelManager(
        default: DefaultTranslationModelManager
    ): TranslationModelManager

    @Binds
    fun bindTranslationWorkerManager(
        default: DefaultTranslationWorkerManager
    ): TranslationWorkerManager
}