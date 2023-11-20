package com.ovais.translatify.options.di

import com.ovais.translatify.options.domain.AccountSettingsUseCase
import com.ovais.translatify.options.domain.DefaultAccountSettingsUseCase
import com.ovais.translatify.options.domain.DefaultGeneralSettingsUseCase
import com.ovais.translatify.options.domain.DefaultOtherSettingsUseCase
import com.ovais.translatify.options.domain.GeneralSettingsUseCase
import com.ovais.translatify.options.domain.OtherSettingsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
interface OptionModule {
    @Binds
    fun bindGeneralSettings(
        default: DefaultGeneralSettingsUseCase
    ): GeneralSettingsUseCase

    @Binds
    fun bindAccountSettingUseCase(
        default: DefaultAccountSettingsUseCase
    ): AccountSettingsUseCase

    @Binds
    fun bindOtherSettingUseCase(
        default: DefaultOtherSettingsUseCase
    ): OtherSettingsUseCase
}