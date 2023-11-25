package com.ovais.translatify.languages.data

interface LanguageRepository {
    suspend fun get(): List<LanguagesData>
    suspend fun getInstalledLanguages(): List<String>
}