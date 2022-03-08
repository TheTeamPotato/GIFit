package com.theteampotato.gifit

import android.os.Bundle

import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

import com.theteampotato.gifit.ui.GIFitTheme

import dagger.hilt.android.AndroidEntryPoint

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.theteampotato.gifit.ui.BottomNavScreen
import com.theteampotato.gifit.ui.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            GIFitTheme {
                NavigationGraph(navController)
            }
        }
    }

}

@Composable
fun HomeScaffold(navController: NavController, navigateTo: (String) -> Unit, content: @Composable (Modifier) -> Unit) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            val currentRoute = remember { mutableStateOf(BottomNavScreen.SearchNavScreen.route) }
            GIFitBottomNavBar(currentDestination = navBackStackEntry?.destination, navigateTo = {
                currentRoute.value = it

                navigateTo(currentRoute.value)
            })
        }
    ) {
        content(Modifier.padding(it))
    }
}