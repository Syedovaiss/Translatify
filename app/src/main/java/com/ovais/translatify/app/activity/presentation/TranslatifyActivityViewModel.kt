package com.ovais.translatify.app.activity.presentation

import androidx.lifecycle.ViewModel
import com.ovais.translatify.app.activity.data.BottomNavUiData
import com.ovais.translatify.app.activity.domain.BottomNavItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TranslatifyActivityViewModel @Inject constructor(
    private val bottomNavItemsUseCase: BottomNavItemsUseCase
) : ViewModel() {

    private val _bottomNavUiData by lazy {
        MutableStateFlow(BottomNavUiData.empty)
    }
    val bottomNavUiData: StateFlow<BottomNavUiData>
        get() = _bottomNavUiData

    init {
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        _bottomNavUiData.value = BottomNavUiData(
            navItems = bottomNavItemsUseCase(),
            canShow = false
        )
    }

    fun updateBottomBarVisibility(visible: Boolean) {
        _bottomNavUiData.value = bottomNavUiData.value.copy(
            canShow = visible
        )
    }
}