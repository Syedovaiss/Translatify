package com.ovais.translatify.app.activity.di

import com.ovais.translatify.app.activity.domain.BottomNavItemsUseCase
import com.ovais.translatify.app.activity.domain.DefaultBottomNavItemsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
interface ActivityBinderModule {

    @Binds
    fun bindBottomNavItemsUseCase(
        default: DefaultBottomNavItemsUseCase
    ): BottomNavItemsUseCase
}