package com.ovais.common.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.sharp.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButtonView(
    onClick: () -> Unit,
    text: String,
    textColor: Color = Color.White,
    fillColor: Color = ButtonColor,
    strokeColor: Color? = null,
    isEnabled: Boolean = true,
    leftIconRes: Any? = null,
    rightIconRes: Any? = null,
    modifier: (() -> Modifier)? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier?.invoke() ?: Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = strokeColor?.let { ButtonDefaults.outlinedShape } ?: ButtonDefaults.shape,
        border = strokeColor?.let {
            ButtonDefaults.outlinedButtonBorder.copy(
                brush = SolidColor(
                    strokeColor
                )
            )
        },
        colors = strokeColor?.let {
            ButtonDefaults.outlinedButtonColors(containerColor = fillColor)
        } ?: ButtonDefaults.buttonColors(containerColor = fillColor),
        enabled = isEnabled
    ) {
        leftIconRes?.let { resource ->
            when (resource) {
                is Int -> Image(
                    painter = painterResource(id = resource),
                    contentDescription = resource.toString()
                )

                is ImageVector ->
                    Image(
                        imageVector = resource,
                        contentDescription = resource.toString()
                    )
            }
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            style = Typography.titleMedium,
            color = textColor
        )
        Spacer(modifier = Modifier.width(4.dp))
        rightIconRes?.let { resource ->
            when (resource) {
                is Int -> Image(
                    painter = painterResource(id = resource),
                    contentDescription = resource.toString()
                )

                is ImageVector ->
                    Image(
                        imageVector = resource,
                        contentDescription = resource.toString()
                    )
            }
        }
    }
}

@Composable
@Preview(widthDp = 360, heightDp = 600, backgroundColor = 0xFFFFFF, showBackground = true)
fun Previews() {
    PrimaryButtonView(
        onClick = {},
        text = "Primary button view",
        leftIconRes = Icons.Sharp.ThumbUp,
        rightIconRes = Icons.Rounded.Delete,
        strokeColor = Color.Yellow
    )
}