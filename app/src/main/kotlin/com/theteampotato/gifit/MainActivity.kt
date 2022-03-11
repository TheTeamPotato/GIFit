package com.theteampotato.gifit

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.theteampotato.gifit.favorites.viewmodel.FavoritesViewModel
import com.theteampotato.gifit.history.viewmodel.HistoryViewModel
import com.theteampotato.gifit.home.viewmodel.SearchViewModel
import com.theteampotato.gifit.ui.BottomNavScreen
import com.theteampotato.gifit.ui.GIFitTheme
import com.theteampotato.gifit.ui.view.GIFitBottomNavBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels()
    private val favoritesViewModel: FavoritesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            GIFitTheme {
                NavigationGraph(
                    navController = navController,
                    searchViewModel = searchViewModel,
                    historyViewModel = historyViewModel,
                    favoritesViewModel = favoritesViewModel
                )
            }
        }
    }

}

@Composable
fun HomeScaffold(
    navController: NavController,
    navigateTo: (String) -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            val currentRoute = remember { mutableStateOf(BottomNavScreen.SearchNavScreen.route) }
            GIFitBottomNavBar(
                currentDestination = navBackStackEntry?.destination,
                backAndNavigateTo = {
                    currentRoute.value = it
                    navController.popBackStack()
                    navController.navigate(currentRoute.value)
                },
                navigateTo = {
                    currentRoute.value = it
                    navigateTo(currentRoute.value)
                })
        }
    ) {
        content(Modifier.padding(it))
    }
}