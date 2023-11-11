package com.ovais.translatify.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ovais.common.composables.ComposeLottieAnimation
import com.ovais.common.composables.Typography


@Composable
fun SplashView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        ComposeLottieAnimation(
            resId = com.ovais.common.R.raw.world_map,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Translatify",
            style = Typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 50.dp
                ),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 320, heightDp = 740)
@Composable
fun SplashPreview() {
    SplashView()
}