package com.ovais.translatify.home.data

sealed interface TranslationActions {

    object Dictionary : TranslationActions
    object Share : TranslationActions
    object Save : TranslationActions
    object Copy : TranslationActions
}