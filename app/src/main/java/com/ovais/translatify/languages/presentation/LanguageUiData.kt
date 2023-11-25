package com.ovais.translatify.languages.presentation

import kotlinx.serialization.Serializable

@Serializable
data class LanguageUiData(
    val title: String,
    val code: String,
    val isInstalled: Boolean,
    val flag: Int
)
