package com.ovais.translatify.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ovais.translatify.app.theme.Typography
import com.ovais.translatify.app.theme.backgroundColor
import com.ovais.translatify.app.theme.bottomNavContainerColor
import com.ovais.translatify.app.theme.iconSelectedTint
import com.ovais.translatify.app.theme.iconTint
import com.ovais.translatify.app.theme.textColorLight


@Composable
fun BottomBar(
    navItems: List<BottomNavItems>,
    canShowTitle: Boolean = false,
    navController: NavHostController
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            )
            .height(60.dp)
            .clip(RoundedCornerShape(12.dp)),
        containerColor = bottomNavContainerColor,
        tonalElevation = 32.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        navItems.forEach { screen ->
            NavigationBarItem(
                label = {
                    if (canShowTitle) {
                        Text(
                            text = screen.title,
                            style = Typography.bodyMedium,
                            color = textColorLight
                        )
                    }
                },
                icon = {
                    Image(
                        painterResource(id = screen.icon),
                        contentDescription = screen.title,
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                },
                selected = currentRoute == screen.routeId,
                onClick = {
                    navController.navigate(screen.routeId) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = iconTint,
                    selectedTextColor = iconSelectedTint,
                    selectedIconColor = iconSelectedTint,
                    unselectedIconColor = iconTint,
                    indicatorColor = backgroundColor
                )
            )
        }
    }

}