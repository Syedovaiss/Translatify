package com.ovais.translatify.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovais.common.ClipboardManager
import com.ovais.common.ads.AdsManager
import com.ovais.common.toaster.ToastManager
import com.ovais.common.toaster.Toaster
import com.ovais.common.utils.EMPTY_STRING
import com.ovais.common.utils.default
import com.ovais.translatify.home.data.SupportedLanguages
import com.ovais.translatify.home.data.TranslationActions
import com.ovais.translatify.home.domain.SupportedLanguageUseCase
import com.ovais.translatify.home.domain.TranslateContentUseCase
import com.ovais.translatify.home.domain.TranslationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val adsManager: AdsManager,
    private val clipboardManager: ClipboardManager,
    private val supportedLanguageUseCase: SupportedLanguageUseCase,
    private val translateContentUseCase: TranslateContentUseCase,
    private val toastManager: ToastManager
) : ViewModel() {
    private var translateFrom = SupportedLanguages.default.title
    private var translateTo = SupportedLanguages.default.title

    private val _areAdsEnabled by lazy {
        MutableStateFlow(adsManager.areAdsEnabled)
    }
    private val _supportedLanguages by lazy {
        MutableStateFlow(supportedLanguageUseCase().map { it.title })
    }
    val supportedLanguages: StateFlow<List<String>>
        get() = _supportedLanguages
    val areAdsEnabled: StateFlow<Boolean>
        get() = _areAdsEnabled

    private val _canShowTranslatedView by lazy {
        MutableStateFlow(false)
    }
    val canShowTranslatedView: StateFlow<Boolean>
        get() = _canShowTranslatedView

    private val _translatedText by lazy {
        MutableStateFlow(EMPTY_STRING)
    }
    val translatedText: StateFlow<String>
        get() = _translatedText

    fun setTranslatedFrom(language: String) {
        translateFrom = language
    }

    fun setTranslateTo(language: String) {
        translateTo = language
    }

    fun onTranslate(text: String) {
        viewModelScope.launch {
            val result = translateContentUseCase(
                source = getLanguage(translateFrom)?.code.default(),
                target = getLanguage(translateTo)?.code.default(),
                text = text
            )
            when (result) {
                is TranslationResult.Completed -> {
                    _translatedText.value = result.text
                    _canShowTranslatedView.value = true

                }

                is TranslationResult.Failed -> {
                    showMessage(
                        title = "Oops! Something Happened!",
                        message = result.error,
                        type = Toaster.Status.ERROR
                    )
                }
            }
        }
    }

    fun onTextUpdate(text: String) {
        if (text.isEmpty()) {
            _canShowTranslatedView.value = false
        }
    }

    private fun showMessage(title: String, message: String, type: Toaster.Status) {
        toastManager.showToast(
            title = title,
            description = message,
            status = type
        )
    }

    fun onActionPerformed(action: TranslationActions, text: String) {
        when (action) {
            is TranslationActions.Save -> {
                showMessage(
                    title = "Saved!!",
                    message = "Translation saved successfully!",
                    type = Toaster.Status.SUCCESS
                )
            }

            is TranslationActions.Copy -> {
                clipboardManager.copy(text)
                showMessage(
                    title = "Copied",
                    message = "Copied to clipboard",
                    type = Toaster.Status.SUCCESS
                )
            }

            is TranslationActions.Share -> {

            }

            is TranslationActions.Dictionary -> {

            }
        }
    }

    private fun getLanguage(text: String): SupportedLanguages? {
        return supportedLanguageUseCase().find { it.title == text }
    }
}