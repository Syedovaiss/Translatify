package com.ovais.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.sharp.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ovais.common.R

@Composable
fun LoaderButtonView(
    onClick: () -> Unit,
    text: String,
    textColor: Color = Color.White,
    fillColor: Color = ButtonColor,
    strokeColor: Color? = null,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    leftIconRes: Any? = null,
    rightIconRes: Any? = null,
    modifier: (() -> Modifier)? = null
) {
    if (isLoading) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(
                R.raw.dots_loader_white
            )
        )
        Row(
            modifier = (
                modifier?.invoke() ?: Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                )
                .clip(
                    RoundedCornerShape(60.dp)
                )
                .background(fillColor)
        ) {
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
        }
    } else {
        PrimaryButtonView(
            onClick,
            text,
            textColor,
            fillColor,
            strokeColor,
            isEnabled,
            leftIconRes,
            rightIconRes,
            modifier
        )
    }
}

@Composable
@Preview(widthDp = 360, heightDp = 600, backgroundColor = 0xFFFFFF, showBackground = true)
fun LoaderButtonPreviews() {
    LoaderButtonView(
        onClick = {},
        text = "Primary button view",
        leftIconRes = Icons.Sharp.ThumbUp,
        rightIconRes = Icons.Rounded.Delete,
        strokeColor = Color.Yellow,
        isLoading = true
    )
}