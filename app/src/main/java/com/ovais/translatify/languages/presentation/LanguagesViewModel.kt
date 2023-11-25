package com.ovais.translatify.languages.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovais.translatify.languages.domain.DeleteLanguageUseCase
import com.ovais.translatify.languages.domain.DownloadLanguageUseCase
import com.ovais.translatify.languages.domain.GetAllInstalledLanguagesUseCase
import com.ovais.translatify.languages.domain.GetAllLanguagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguagesViewModel @Inject constructor(
    private val getAllInstalledLanguagesUseCase: GetAllInstalledLanguagesUseCase,
    private val getAllLanguagesUseCase: GetAllLanguagesUseCase,
    private val languageUiDataMapper: LanguageUiDataMapper,
    private val deleteLanguageUseCase: DeleteLanguageUseCase,
    private val downloadLanguageUseCase: DownloadLanguageUseCase
) : ViewModel() {


    private val _uiData by lazy {
        MutableStateFlow<List<LanguageUiData>>(listOf())
    }
    val uiData: StateFlow<List<LanguageUiData>>
        get() = _uiData

    private val _isLoading by lazy {
        MutableStateFlow(true)
    }
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    init {
        initLanguages()
    }

    private fun initLanguages() {
        viewModelScope.launch {
            val supportedLanguages = getAllLanguagesUseCase()
            val installedLanguages = getAllInstalledLanguagesUseCase()
            _uiData.value = languageUiDataMapper.map(
                allLangauges = supportedLanguages,
                installed = installedLanguages
            )
            _isLoading.value = false
        }
    }

    fun onDownloadLanguage(data: LanguageUiData) {
        viewModelScope.launch {
            downloadLanguageUseCase(data.code)
        }
    }

    fun onDeleteLanguage(data: LanguageUiData) {
        viewModelScope.launch {
            deleteLanguageUseCase(data.code)
        }
    }
}