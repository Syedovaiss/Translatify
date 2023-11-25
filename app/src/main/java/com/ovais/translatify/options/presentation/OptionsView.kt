package com.ovais.translatify.options.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ovais.translatify.app.theme.Typography
import com.ovais.translatify.app.theme.textColorDark
import com.ovais.translatify.app.theme.textColorLight


@Composable
fun OptionsView(
    navController: NavController? = null,
    scaffoldPadding: PaddingValues = PaddingValues(0.dp),
    viewModel: OptionsViewModel = hiltViewModel()
) {
    viewModel.setupNavigation(navController = navController)
    val generalSettings by viewModel.generalSettings.collectAsState()
    val accountSettings by viewModel.accountSettings.collectAsState()
    val otherSettings by viewModel.otherSettings.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding)
    ) {
        Text(
            text = "General Settings",
            style = Typography.titleLarge,
            color = textColorLight,
            modifier = Modifier.padding(
                top = 24.dp,
                start = 16.dp
            )
        )

        LazyColumn {
            items(generalSettings) { item ->
                OptionItemView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                    item
                ) { type ->
                    viewModel.onAction(type)
                }
            }
        }
        Text(
            text = "Account Settings",
            style = Typography.titleLarge,
            color = textColorLight,
            modifier = Modifier.padding(
                top = 24.dp,
                start = 16.dp
            )
        )

        LazyColumn {
            items(accountSettings) { item ->
                OptionItemView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                    item
                ) { type ->
                    viewModel.onAction(type)
                }
            }
        }
        Text(
            text = "Other Settings",
            style = Typography.titleLarge,
            color = textColorLight,
            modifier = Modifier.padding(
                top = 24.dp,
                start = 16.dp
            )
        )

        LazyColumn {
            items(otherSettings) { item ->
                OptionItemView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                    item
                ) { type ->
                    viewModel.onAction(type)
                }
            }
        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun MenuOptionPreview() {
    OptionsView()
}