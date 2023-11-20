package com.ovais.translatify.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovais.common.ClipboardManager
import com.ovais.common.ads.AdsManager
import com.ovais.common.utils.EMPTY_STRING
import com.ovais.common.utils.default
import com.ovais.translatify.home.data.SupportedLanguages
import com.ovais.translatify.home.data.TranslationActions
import com.ovais.translatify.home.domain.SupportedLanguageUseCase
import com.ovais.translatify.home.domain.TranslateContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val adsManager: AdsManager,
    private val clipboardManager: ClipboardManager,
    private val supportedLanguageUseCase: SupportedLanguageUseCase,
    private val translateContentUseCase: TranslateContentUseCase
) : ViewModel() {
    private var translateFrom = EMPTY_STRING
    private var translateTo = EMPTY_STRING

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
            translateContentUseCase(
                getLanguage(translateFrom)?.code.default(),
                getLanguage(translateTo)?.code.default(),
                text
            ) {
                _translatedText.value = it
                _canShowTranslatedView.value = true
            }
        }
    }

    fun onTextUpdate(text: String) {
        if (text.isEmpty()) {
            _canShowTranslatedView.value = false
        }
    }

    fun onActionPerformed(action: TranslationActions, text: String) {
        when (action) {
            is TranslationActions.Save -> {

            }

            is TranslationActions.Copy -> clipboardManager.copy(text)

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