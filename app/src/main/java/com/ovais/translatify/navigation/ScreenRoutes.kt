package com.ovais.translatify.navigation

import androidx.annotation.DrawableRes
import com.ovais.translatify.R

private const val SCREEN_PREFIX = "screen_"

interface ScreenRoutes {
    val routeId: String
}

sealed class Screens(
    override val routeId: String,
    val title: String,
    @DrawableRes val iconRes: Int? = null
) : ScreenRoutes {

    object Splash : Screens(
        routeId = buildRoute("splash"),
        title = "Splash"
    )

    object Home : Screens(
        routeId = buildRoute("home"),
        title = "Home",
        iconRes = R.drawable.ic_home
    )

    object VoiceTranslate : Screens(
        routeId = buildRoute("voice_translate"),
        title = "Voice Translate",
        iconRes = R.drawable.ic_microphone

    )

    object ScanWithCamera : Screens(
        routeId = buildRoute("scan_with_camera"),
        title = "Scan",
        iconRes = R.drawable.ic_camera
    )

    object SavedTranslations : Screens(
        routeId = buildRoute("saved_translation"),
        title = "Saved Translation",
        iconRes = R.drawable.ic_saved
    )

    object Options : Screens(
        routeId = buildRoute("option"),
        title = "Options",
        iconRes = R.drawable.ic_menu
    )
    object AllLanguages : Screens(
        routeId = buildRoute("all_languages"),
        title = "All Languages"
    )


    fun withArgs(vararg args: String): String {
        return buildString {
            append(routeId)
            append("/$args")
        }
    }
}

private fun buildRoute(id: String) = buildString {
    append(SCREEN_PREFIX)
    append(id)
}