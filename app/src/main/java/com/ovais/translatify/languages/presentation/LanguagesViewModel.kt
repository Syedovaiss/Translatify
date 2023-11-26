package com.ovais.translatify.languages.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovais.common.toaster.ToastManager
import com.ovais.common.toaster.Toaster
import com.ovais.translatify.languages.domain.DeleteLanguageUseCase
import com.ovais.translatify.languages.domain.DeletionResult
import com.ovais.translatify.languages.domain.DownloadLanguageUseCase
import com.ovais.translatify.languages.domain.DownloadResults
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
    private val downloadLanguageUseCase: DownloadLanguageUseCase,
    private val toastManager: ToastManager
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
            when (val result = downloadLanguageUseCase(data.code)) {
                is DownloadResults.Downloaded -> {
                    showMessage(
                        title = "${data.title} Installed!",
                        description = "Your ${data.title} language is installed on device.",
                        status = Toaster.Status.SUCCESS
                    )
                }

                is DownloadResults.Failed -> {
                    showMessage(
                        title = "Failed to install language!",
                        description = "Your ${data.title} language is " +
                                "not installed because ${result.error}",
                        status = Toaster.Status.ERROR
                    )
                }
            }
        }
    }

    private fun showMessage(title: String, description: String, status: Toaster.Status) {
        toastManager.showToast(
            title = title,
            description = description,
            status = status
        )
    }

    fun onDeleteLanguage(data: LanguageUiData) {
        viewModelScope.launch {
            when (val result = deleteLanguageUseCase(data.code)) {
                is DeletionResult.Deleted -> {
                    showMessage(
                        title = "${data.title} Deleted!",
                        description = "Your ${data.title} language is deleted from device.",
                        status = Toaster.Status.SUCCESS
                    )
                }

                is DeletionResult.Failed -> {
                    showMessage(
                        title = "Failed to delete language!",
                        description = "Your ${data.title} language is " +
                                "not deleted because ${result.error}",
                        status = Toaster.Status.ERROR
                    )
                }
            }
        }
    }
}