package com.ovais.translatify.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ovais.common.utils.EMPTY_STRING
import com.ovais.translatify.R
import com.ovais.translatify.app.theme.Typography
import com.ovais.translatify.app.theme.textColorLight
import com.ovais.translatify.home.data.TranslationActions


@Composable
fun TranslatedTextView(
    modifier: Modifier = Modifier,
    text: String = EMPTY_STRING,
    onAction: (TranslationActions) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color.LightGray.copy(alpha = 0.1f))
                .padding(
                    all = 16.dp
                )
        ) {
            Text(
                text = text,
                style = Typography.headlineLarge,
                color = textColorLight,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 8.dp
                    ),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_dictionary),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            horizontal = 4.dp
                        )
                        .clickable {
                            onAction(TranslationActions.Dictionary)
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_add_bookmark),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            horizontal = 4.dp
                        )
                        .clickable {
                            onAction(TranslationActions.Save)
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_copy),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            horizontal = 4.dp
                        )
                        .clickable {
                            onAction(TranslationActions.Copy)
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            horizontal = 4.dp
                        )
                        .clickable {
                            onAction(TranslationActions.Share)
                        }
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000,
    widthDp = 320,
    heightDp = 300
)
@Composable
fun TranslatedTextViewPreview() {
    TranslatedTextView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        text = "Lorem ipsum abcds dfvs sfbfd vdbvs"
    ) {

    }
}

