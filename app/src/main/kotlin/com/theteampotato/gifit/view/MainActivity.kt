package com.theteampotato.gifit

import android.os.Bundle
import android.view.View
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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.theteampotato.gifit.favorites.viewmodel.FavoritesViewModel
import com.theteampotato.gifit.history.viewmodel.HistoryViewModel
import com.theteampotato.gifit.home.viewmodel.SearchViewModel
import com.theteampotato.gifit.language_selection.viewmodel.LanguageSelectionViewModel
import com.theteampotato.gifit.navigation.NavigationGraph
import com.theteampotato.gifit.splash.viewmodel.SplashViewModel
import com.theteampotato.gifit.ui.BottomNavScreen
import com.theteampotato.gifit.ui.GIFitTheme
import com.theteampotato.gifit.ui.view.GIFitBottomNavBar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val languageSelectionViewModel: LanguageSelectionViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels()
    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private val mainViewModel: SplashViewModel by viewModels()
    private var isSelectedLanguage: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        val content = findViewById<View>(android.R.id.content)

        lifecycleScope.launchWhenCreated {
            isSelectedLanguage = mainViewModel.getIsSelectedLanguage()
            Timber.d("start 0 - isSelectedLanguage : ${isSelectedLanguage}")
            content.viewTreeObserver.addOnPreDrawListener {
                Timber.d("start 1 - isSelectedLanguage : ${isSelectedLanguage}")
                (isSelectedLanguage!!)
            }

            setContent {
                val navController = rememberNavController()
                GIFitTheme {
                    NavigationGraph(
                        navController = navController,
                        isSelectedLanguage = isSelectedLanguage,
                        languageSelectionViewModel = languageSelectionViewModel,
                        searchViewModel = searchViewModel,
                        historyViewModel = historyViewModel,
                        favoritesViewModel = favoritesViewModel
                    )
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        searchViewModel.releaseResources()
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
                navigateTo = {
                    currentRoute.value = it
                    navigateTo(currentRoute.value)
                })
        }
    ) {
        content(Modifier.padding(it))
    }
}