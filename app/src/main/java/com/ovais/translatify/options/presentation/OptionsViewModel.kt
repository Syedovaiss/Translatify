package com.ovais.translatify.options.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovais.translatify.options.data.SettingOptionItems
import com.ovais.translatify.options.data.SettingOptionType
import com.ovais.translatify.options.domain.AccountSettingsUseCase
import com.ovais.translatify.options.domain.GeneralSettingsUseCase
import com.ovais.translatify.options.domain.OtherSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor(
    private val generalSettingsUseCase: GeneralSettingsUseCase,
    private val accountSettingsUseCase: AccountSettingsUseCase,
    private val otherSettingsUseCase: OtherSettingsUseCase
) : ViewModel() {

    private val _generalSetting by lazy {
        MutableStateFlow<List<SettingOptionItems>>(listOf())
    }
    val generalSettings: StateFlow<List<SettingOptionItems>>
        get() = _generalSetting

    private val _accountSettings by lazy {
        MutableStateFlow<List<SettingOptionItems>>(listOf())
    }
    val accountSettings: StateFlow<List<SettingOptionItems>>
        get() = _accountSettings

    private val _otherSettings by lazy {
        MutableStateFlow(otherSettingsUseCase())
    }
    val otherSettings: StateFlow<List<SettingOptionItems>>
        get() = _otherSettings


    init {
        initSettings()
    }

    private fun initSettings() {
        viewModelScope.launch {
            _generalSetting.value = generalSettingsUseCase()
            accountSettingsUseCase {
                _accountSettings.value = it
            }
        }
    }

    fun onAction(action: SettingOptionType) {

    }
}