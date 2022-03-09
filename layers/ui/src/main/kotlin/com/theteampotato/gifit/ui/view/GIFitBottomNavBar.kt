package com.theteampotato.gifit.ui.view

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.theteampotato.gifit.ui.BottomNavScreen
import com.theteampotato.gifit.ui.getBottomNavigationScreenList
import timber.log.Timber

@Composable
fun GIFitBottomNavBar(
    currentDestination: NavDestination?,
    navigateTo: (String) -> Unit = {},
    backAndNavigateTo: (String) -> Unit = {},
    items: List<BottomNavScreen> = getBottomNavigationScreenList()
) {
    BottomNavigation(backgroundColor = Color.White) {
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = null) },
                label = { Text(stringResource(id = screen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route?.split('/')?.first() == screen.route } == true,
                alwaysShowLabel = true,
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray,
                onClick = {
                    Timber.d("screen.route is ${screen.route}")
                    Timber.d("currentDestination is $currentDestination")

                    if (currentDestination?.route?.split('/')?.size!! > 1)
                        backAndNavigateTo(screen.route)
                    else
                        navigateTo(screen.route)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewGIFitBottomNavBar() {
    GIFitBottomNavBar(null, { Timber.d("Navigating to $it") }, items = getBottomNavigationScreenList())
}