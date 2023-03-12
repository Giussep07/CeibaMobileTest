package com.giussepr.ceiba.presentation

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.giussepr.ceiba.ui.presentation.navigation.AppNavigation
import com.giussepr.ceiba.ui.presentation.navigation.AppScreens
import com.giussepr.ceiba.ui.presentation.screens.MainActivity
import com.giussepr.ceiba.ui.presentation.theme.CeibaTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        hiltTestRule.inject()

        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            CeibaTheme {
                AppNavigation(navController = navController, startDestination = AppScreens.Home.route)
            }
        }
    }

    @Test
    fun testUserCardItemOnClickNavigateToPostScreen() {
        composeTestRule.onRoot().printToLog("TAG")

        composeTestRule.onAllNodes(
            hasTestTag("seePublicationButtonTag")
                    and
                    hasClickAction()
        )
            .onFirst()
            .performClick()

        val currentDestination = navController.currentBackStackEntry?.destination?.route

        Assert.assertTrue(currentDestination?.contains(AppScreens.PublicationsScreen.route) ?: false)
    }
}