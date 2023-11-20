package com.ovais.translatify.app.activity.data

import com.ovais.translatify.app.ui.BottomNavItems
import kotlinx.serialization.Serializable

@Serializable
data class BottomNavUiData(
    val canShow: Boolean = true,
    val navItems: List<BottomNavItems>
) {
    companion object {
        val empty = BottomNavUiData(
            canShow = false,
            navItems = listOf()
        )
    }
}
