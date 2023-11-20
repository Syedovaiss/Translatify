package com.ovais.translatify.app.ui

import kotlinx.serialization.Serializable

@Serializable
data class BottomNavItems(
    val title: String,
    val icon: Int,
    val routeId: String
)
