package com.ovais.translatify.splash.presentation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ovais.common.composables.ComposeLottieAnimation
import com.ovais.translatify.R


@Composable
fun SplashView(
    navController: NavHostController? = null,
    viewModel: SplashViewModel = hiltViewModel(),
    scaffoldPadding: PaddingValues = PaddingValues(0.dp)
) {
    viewModel.setupNavigation(navController)

    val horizontalBias by rememberInfiniteTransition()
        .animateFloat(
            initialValue = -1F,
            targetValue = 1F,
            animationSpec = infiniteRepeatable(
                animation = tween(3000),
                repeatMode = RepeatMode.Reverse,
            ),
        )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding),
        contentAlignment = Alignment.BottomCenter
    ) {
        ComposeLottieAnimation(
            resId = com.ovais.common.R.raw.world_map,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(
                id = R.drawable.translatify_logo
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 24.dp
                )
                .width(250.dp)
                .height(250.dp),
            alignment = BiasAlignment(horizontalBias, 0F)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 320, heightDp = 740)
@Composable
fun SplashPreview() {
    SplashView()
}