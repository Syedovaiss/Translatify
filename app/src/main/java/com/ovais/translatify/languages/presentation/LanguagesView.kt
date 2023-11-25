package com.ovais.translatify.languages.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController


@Composable
fun LanguagesView(
    navController: NavHostController? = null,
    scaffoldPadding: PaddingValues = PaddingValues(all = 0.dp),
    viewModel: LanguagesViewModel = hiltViewModel()
) {
    val uiData by viewModel.uiData.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding)
    ) {
        Image(
            painter = painterResource(
                id = com.ovais.common.R.drawable.ic_back
            ),
            contentDescription = null,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                )
                .clickable {
                    navController?.popBackStack()
                }
        )

        LazyColumn {
            items(uiData) { item ->
                LanguageItemView(
                    uiData = item,
                    onDelete = { data ->
                        viewModel.onDeleteLanguage(data)
                    },
                    onInstall = { data ->
                        viewModel.onDownloadLanguage(data)
                    },
                    modifier = Modifier.fillMaxWidth().padding(
                        vertical = 8.dp,
                        horizontal = 16.dp
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun LanguagesPreview() {
    LanguagesView()
}