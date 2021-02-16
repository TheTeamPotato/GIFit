package com.theteampotato.gifit.ui.view

import android.util.Log
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import timber.log.Timber

@Composable
fun GIFitBottomNavBar(
    currentRoute: String,
    navigateTo: (String) -> Unit,
    items: List<BottomNavScreens>
) {
    BottomNavigation(backgroundColor = Color.White) {
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon) },
                label = { Text(stringResource(id = screen.resourceId)) },
                selected = currentRoute == screen.route,
                alwaysShowLabels = true,
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray,
                onClick = {
                    navigateTo(screen.route)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewGIFitBottomNavBar() {
    GIFitBottomNavBar("Search", { Timber.d("Navigating to $it") }, getScreens())
}