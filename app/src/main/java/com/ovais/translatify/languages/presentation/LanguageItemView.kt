package com.ovais.translatify.languages.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ovais.translatify.R
import com.ovais.translatify.app.theme.Typography
import com.ovais.translatify.app.theme.textColorLight


@Composable
fun LanguageItemView(
    modifier: Modifier = Modifier,
    uiData: LanguageUiData,
    onDelete: (LanguageUiData) -> Unit,
    onInstall: (LanguageUiData) -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.LightGray.copy(0.1f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(
                    all = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = uiData.flag),
                contentDescription = null
            )

            Text(
                text = uiData.title,
                style = Typography.titleMedium,
                color = textColorLight,
                modifier = Modifier.padding(
                    start = 8.dp,
                    end = 16.dp
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            if (uiData.isInstalled) {
                Image(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onDelete(uiData)
                    }
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_download),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onInstall(uiData)
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF000000, widthDp = 200)
@Composable
fun LanguagesItemPreview() {
    LanguageItemView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        uiData = LanguageUiData(
            title = "Urdu",
            code = "ur",
            isInstalled = true,
            flag = com.ovais.common.R.drawable.ic_pakistan
        ),
        onDelete = {},
        onInstall = {}
    )
}