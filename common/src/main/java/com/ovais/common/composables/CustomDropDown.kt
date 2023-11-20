package com.ovais.common.composables


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ovais.common.utils.EMPTY_STRING

@Composable
fun CustomDropDown(
    modifier: Modifier = Modifier,
    defaultText: String = EMPTY_STRING,
    @DrawableRes icon: Int? = null,
    items: List<String> = emptyList(),
    onValueSelected: (String) -> Unit = {},
    containerColor: Color = Color.Gray.copy(alpha = 0.2f),
    contentColor: Color = Color.Black,
    dropDownHeight: Dp = 45.dp,
    iconHeight: Dp = 24.dp,
    iconWidth: Dp = 24.dp,
    defaultIconTint: Color = Color.Black,
    horizontalPadding: Dp = 16.dp,
    verticalPadding: Dp = 8.dp,
    textColor: Color = Color.Black
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember {
        mutableStateOf(EMPTY_STRING)
    }
    Box(modifier = modifier) {
        Button(
            onClick = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = horizontalPadding,
                    vertical = verticalPadding
                )
                .height(dropDownHeight),
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (selectedText.isEmpty()) {
                    Text(
                        defaultText,
                        style = Typography.bodyMedium,
                        color = textColor
                    )
                } else {
                    Text(
                        selectedText,
                        style = Typography.bodyMedium,
                        color = textColor
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                icon?.let {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier
                            .width(iconWidth)
                            .height(iconHeight)
                    )
                } ?: run {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier
                            .width(iconWidth)
                            .height(iconHeight),
                        tint = defaultIconTint
                    )
                }
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ) {
            items.forEachIndexed { index, listItem ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = listItem,
                            style = Typography.bodyMedium,
                            color = textColor
                        )
                    },
                    onClick = {
                        selectedText = listItem
                        onValueSelected(listItem)
                        expanded = false
                    }
                )
                if (index < items.lastIndex) {
                    Divider(
                        color = Color.Gray.copy(0.2f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 8.dp
                            )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomDropDownPreview() {
    CustomDropDown(
        items = listOf("followers", "public", "only me"),
        onValueSelected = {
        }
    )
}