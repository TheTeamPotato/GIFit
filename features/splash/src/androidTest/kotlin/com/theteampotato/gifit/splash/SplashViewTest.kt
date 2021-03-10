package com.theteampotato.gifit.splash

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText

import com.theteampotato.gifit.splash.view.SplashScreen
import com.theteampotato.gifit.ui.GIFitTheme

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            GIFitTheme {
                SplashScreen()
            }
        }
    }

    @Test
    fun splash_card_contains_app_name() {
        composeTestRule.onNodeWithText("GIFit").assertExists()
    }

}