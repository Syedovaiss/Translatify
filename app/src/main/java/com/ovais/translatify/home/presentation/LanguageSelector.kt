package com.ovais.translatify.home.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ovais.common.composables.CustomDropDown
import com.ovais.translatify.R
import com.ovais.translatify.app.theme.backgroundColor
import com.ovais.translatify.app.theme.darkBlue


@Composable
fun LanguageSelector(
    modifier: Modifier = Modifier,
    supportedLanguages: List<String>,
    from: (String) -> Unit,
    to: (String) -> Unit,
    @DrawableRes centerIcon: Int = R.drawable.ic_change,
    containerColor: Color = Color.White,
    textColor: Color = Color.Black
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(7f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CustomDropDown(
                defaultText = "English",
                modifier = Modifier
                    .weight(3f),
                horizontalPadding = 0.dp,
                containerColor = Color.Transparent,
                items = supportedLanguages,
                onValueSelected = {
                    from(it)
                },
                contentColor = textColor,
                textColor = textColor,
                defaultIconTint = textColor
            )
            Box(
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(20.dp),
                        color = darkBlue
                    )
                    .padding(
                        all = 4.dp
                    )
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = centerIcon),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            CustomDropDown(
                defaultText = "Chinese",
                modifier = Modifier
                    .weight(3f),
                horizontalPadding = 0.dp,
                containerColor = Color.Transparent,
                items = supportedLanguages,
                onValueSelected = {
                    to(it)
                },
                contentColor = textColor,
                textColor = textColor,
                defaultIconTint = textColor
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 320)
@Composable
fun LanguageSelectorPreview() {
    val selectedLanguages = listOf(
        "English",
        "Chinese",
        "Hindi",
        "Urdu",
        "French",
        "Japanese"
    )
    LanguageSelector(
        supportedLanguages = selectedLanguages,
        from = {},
        to = {},
        containerColor = backgroundColor,
    )
}