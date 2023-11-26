package com.ovais.translatify.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ovais.common.ads.ui.BannerAdsView
import com.ovais.common.composables.Action
import com.ovais.common.composables.CustomTextField
import com.ovais.common.composables.getActivity
import com.ovais.common.utils.EMPTY_STRING
import com.ovais.common.utils.shareContent
import com.ovais.translatify.app.activity.presentation.TranslatifyActivity
import com.ovais.translatify.app.theme.Typography
import com.ovais.translatify.app.theme.languageSelectorBackground
import com.ovais.translatify.app.theme.textColorDark
import com.ovais.translatify.app.theme.textColorLight

@Composable
fun HomeView(
    navController: NavHostController? = null,
    scaffoldPadding: PaddingValues = PaddingValues(0.dp),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val areAdsEnabled by viewModel.areAdsEnabled.collectAsState()
    val scrollState = rememberScrollState()
    var translationText by remember {
        mutableStateOf(EMPTY_STRING)
    }
    val supportedLanguages by viewModel.supportedLanguages.collectAsState()
    val translatedText by viewModel.translatedText.collectAsState()
    val canShowTranslatedView by viewModel.canShowTranslatedView.collectAsState()
    LocalContext.current.getActivity<TranslatifyActivity>()
        ?.activityViewModel
        ?.updateBottomBarVisibility(true)
    val shareText by viewModel.shareText.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding)
            .verticalScroll(scrollState)
    ) {
        LanguageSelector(
            supportedLanguages = supportedLanguages,
            from = {
                viewModel.setTranslatedFrom(it)
            },
            to = {
                viewModel.setTranslateTo(it)
            },
            containerColor = languageSelectorBackground,
            textColor = textColorDark,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                )
                .height(50.dp)
        )
        if (areAdsEnabled) {
            BannerAdsView(
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        CustomTextField(
            enabled = true,
            label = EMPTY_STRING,
            labelStyle = Typography.bodyMedium,
            placeholderText = "Type Here....",
            placeholderStyle = Typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onValueChanged = { text ->
                translationText = text
                viewModel.onTextUpdate(text)
            },
            fieldHeight = 200,
            maxLines = 200,
            placeholderColor = textColorDark,
            containerColor = Color.LightGray.copy(alpha = 0.1f),
            textColor = textColorLight,
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text,
            onKeyboardAction = { action ->
                if (action is Action.Search) {
                    viewModel.onTranslate(translationText)
                }
            }
        )

        if (canShowTranslatedView) {
            TranslatedTextView(
                text = translatedText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    )
            ) { action ->
                viewModel.onActionPerformed(action, translatedText)
            }
        }
        if (shareText.isNotEmpty()) {
            LocalContext.current.getActivity<TranslatifyActivity>()
                ?.shareContent(shareText)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun HomePreview() {
    HomeView()
}