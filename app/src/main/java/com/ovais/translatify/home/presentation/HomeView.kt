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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ovais.common.composables.Action
import com.ovais.common.composables.CustomTextField
import com.ovais.common.composables.getActivity
import com.ovais.common.utils.EMPTY_STRING
import com.ovais.translatify.app.activity.presentation.TranslatifyActivity
import com.ovais.translatify.app.theme.Typography
import com.ovais.translatify.app.theme.languageSelectorBackground
import com.ovais.translatify.app.theme.textColorDark
import com.ovais.translatify.app.theme.textColorLight

@Composable
fun HomeView(
    navController: NavHostController? = null,
    scaffoldPadding: PaddingValues = PaddingValues(0.dp)
) {
    val scrollState = rememberScrollState()
    LocalContext.current.getActivity<TranslatifyActivity>()
        ?.activityViewModel
        ?.updateBottomBarVisibility(true)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding)
            .verticalScroll(scrollState)
    ) {
        LanguageSelector(
            supportedLanguages = listOf("English", "Urdu"),
            from = {

            },
            to = {

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

        CustomTextField(
            enabled = true,
            label = EMPTY_STRING,
            labelStyle = Typography.bodyMedium,
            placeholderText = "Type Here....",
            placeholderStyle = Typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onValueChanged = {

            },
            fieldHeight = 300,
            maxLines = 1000,
            placeholderColor = textColorDark,
            containerColor = Color.LightGray.copy(alpha = 0.1f),
            textColor = textColorLight,
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text,
            onKeyboardAction = { action ->
                if (action is Action.Search) {
                    // todo trigger value
                }
            }
        )

        TranslatedTextView(
            text = "How are you bro",
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                )
        ) { action ->

        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun HomePreview() {
    HomeView()
}