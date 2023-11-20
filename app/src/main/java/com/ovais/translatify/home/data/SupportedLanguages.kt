package com.ovais.translatify.home.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SupportedLanguages(
    @SerialName("code")
    val code: String,
    @SerialName("lang")
    val lang: String,
    @SerialName("title")
    val title: String
) {
    companion object {
        val default = SupportedLanguages(
            code = "en",
            lang = "ENGLISH",
            title = "English"
        )
    }
}