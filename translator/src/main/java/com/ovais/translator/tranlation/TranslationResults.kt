package com.ovais.translator.tranlation

sealed interface TranslationResults {
    data class Translated(val text: String) : TranslationResults
    data class Failed(val error: String) : TranslationResults
}