package com.ovais.translatify.app.activity.domain

import com.ovais.common.UseCase
import com.ovais.common.utils.default
import com.ovais.translatify.app.ui.BottomNavItems
import com.ovais.translatify.navigation.Screens
import javax.inject.Inject

interface BottomNavItemsUseCase : UseCase<List<BottomNavItems>>

class DefaultBottomNavItemsUseCase @Inject constructor() : BottomNavItemsUseCase {

    override fun invoke(): List<BottomNavItems> {
        return arrayListOf<BottomNavItems>().apply {
            add(
                BottomNavItems(
                    title = Screens.Home.title,
                    icon = Screens.Home.iconRes.default(),
                    routeId = Screens.Home.routeId
                )
            )
            add(
                BottomNavItems(
                    title = Screens.VoiceTranslate.title,
                    icon = Screens.VoiceTranslate.iconRes.default(),
                    routeId = Screens.VoiceTranslate.routeId
                )
            )
            add(
                BottomNavItems(
                    title = Screens.ScanWithCamera.title,
                    icon = Screens.ScanWithCamera.iconRes.default(),
                    routeId = Screens.ScanWithCamera.routeId
                )
            )
            add(
                BottomNavItems(
                    title = Screens.SavedTranslations.title,
                    icon = Screens.SavedTranslations.iconRes.default(),
                    routeId = Screens.SavedTranslations.routeId
                )
            )
            add(
                BottomNavItems(
                    title = Screens.Options.title,
                    icon = Screens.Options.iconRes.default(),
                    routeId = Screens.Options.routeId
                )
            )
        }
    }
}