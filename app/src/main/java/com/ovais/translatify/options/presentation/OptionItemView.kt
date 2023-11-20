package com.ovais.translatify.options.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ovais.translatify.R
import com.ovais.translatify.app.theme.Typography
import com.ovais.translatify.app.theme.textColorDark
import com.ovais.translatify.options.data.SettingOptionItems
import com.ovais.translatify.options.data.SettingOptionType

@Composable
fun OptionItemView(
    modifier: Modifier = Modifier,
    item: SettingOptionItems,
    onClick: (SettingOptionType) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.LightGray.copy(0.1f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(
                    all = 12.dp
                )
                .clickable { onClick(item.type) }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = item.icon
                    ),
                    contentDescription = null
                )
                Text(
                    text = item.title,
                    style = Typography.titleMedium,
                    color = textColorDark,
                    modifier = Modifier
                        .padding(
                            start = 8.dp
                        )
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = textColorDark
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF00000)
@Composable
fun OptionItemViewPreview() {
    OptionItemView(
        item = SettingOptionItems(
            icon = R.drawable.ic_help_and_support,
            title = "Help & Support",
            type = SettingOptionType.HelpAndSupport
        )
    ) {

    }
}