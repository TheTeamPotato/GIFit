package com.theteampotato.gifit.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.theteampotato.gifit.data.LocaleManager
import com.theteampotato.gifit.data.datastore.preferencesDataStore
import com.theteampotato.gifit.favorites.viewmodel.FavoritesViewModel
import com.theteampotato.gifit.history.viewmodel.HistoryViewModel
import com.theteampotato.gifit.home.viewmodel.SearchViewModel
import com.theteampotato.gifit.language_selection.viewmodel.LanguageSelectionViewModel
import com.theteampotato.gifit.navigation.NavigationGraph
import com.theteampotato.gifit.navigation.getStartDestination
import com.theteampotato.gifit.splash.viewmodel.SplashViewModel
import com.theteampotato.gifit.ui.BottomNavScreen
import com.theteampotato.gifit.ui.GIFitTheme
import com.theteampotato.gifit.ui.view.GIFitBottomNavBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

val LocaleActiveManager = compositionLocalOf<LocaleManager> { error("No locale manager found!") }

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels()
    private val languageSelectionViewModel: LanguageSelectionViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private val splashViewModel: SplashViewModel by viewModels()

    lateinit var localeManager: LocaleManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        localeManager = LocaleManager(this, preferencesDataStore)

        var keepSplashScreen = true
        var selectedLanguage: String?

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                keepSplashScreen
            }
        }

        lifecycleScope.launchWhenCreated {
            localeManager.setLocale()

            selectedLanguage = splashViewModel.getIsSelectedLanguage()

            delay(2000)
            keepSplashScreen = false

            setContent {
                CompositionLocalProvider(LocaleActiveManager provides localeManager) {
                    val navController = rememberNavController()
                    GIFitTheme {
                        NavigationGraph(
                            navController = navController,
                            startDestination = getStartDestination(selectedLanguage != null),
                            languageSelectionViewModel = languageSelectionViewModel,
                            searchViewModel = searchViewModel,
                            historyViewModel = historyViewModel,
                            favoritesViewModel = favoritesViewModel
                        )
                    }
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