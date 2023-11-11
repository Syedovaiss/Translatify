package com.ovais.common.composables

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun ComposeLottieAnimation(
    @RawRes resId: Int,
    modifier: Modifier,
    iteration: Int = LottieConstants.IterateForever,
    restartOnPlay: Boolean = true,
    contentScale: ContentScale = ContentScale.Fit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId))
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = iteration,
        restartOnPlay = restartOnPlay,
        contentScale = contentScale
    )
}