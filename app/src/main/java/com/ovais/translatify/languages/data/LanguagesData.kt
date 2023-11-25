package com.ovais.translatify.languages.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LanguagesData(
    @SerialName("code")
    val code: String,
    @SerialName("lang")
    val lang: String,
    @SerialName("title")
    val title: String
) {
    companion object {
        val default = LanguagesData(
            code = "en",
            lang = "ENGLISH",
            title = "English"
        )
    }
}