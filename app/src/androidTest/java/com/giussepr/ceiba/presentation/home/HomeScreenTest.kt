package com.giussepr.ceiba.presentation.home

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performTextReplacement
import androidx.navigation.compose.rememberNavController
import com.giussepr.ceiba.R
import com.giussepr.ceiba.ui.presentation.screens.MainActivity
import com.giussepr.ceiba.ui.presentation.screens.home.HomeScreen
import com.giussepr.ceiba.ui.presentation.screens.home.HomeViewModel
import com.giussepr.ceiba.ui.presentation.theme.CeibaTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltTestRule.inject()

        composeTestRule.activity.setContent {
            CeibaTheme {
                HomeScreen(
                    navController = rememberNavController(),
                    viewModel = composeTestRule.activity.viewModels<HomeViewModel>().value
                )
            }
        }
    }

    @Test
    fun testSearchLetterByLetterShowResultsOnScreen() {

        val searchTextFieldLabel = composeTestRule.activity.getString(R.string.search_user)

        composeTestRule.onAllNodes(hasTestTag("userCardItem")).assertCountEquals(4)
        // Search by letter c
        composeTestRule.onNode(hasText(searchTextFieldLabel)).performTextReplacement("c")
        composeTestRule.onAllNodes(hasTestTag("userCardItem")).assertCountEquals(2)
        // add l to search text field
        composeTestRule.onNode(hasText(searchTextFieldLabel)).performTextReplacement("cl")
        composeTestRule.onAllNodes(hasTestTag("userCardItem")).assertCountEquals(1)
        // add e to search text field
        composeTestRule.onNode(hasText(searchTextFieldLabel)).performTextReplacement("cle")
        composeTestRule.onAllNodes(hasTestTag("userCardItem")).assertCountEquals(1)
    }

    @Test
    fun testSearchTextFieldClearShowOriginalList() {
        val searchTextFieldLabel = composeTestRule.activity.getString(R.string.search_user)

        composeTestRule.onAllNodes(hasTestTag("userCardItem")).assertCountEquals(4)

        composeTestRule.onNode(hasText(searchTextFieldLabel)).performTextReplacement("c")
        composeTestRule.onAllNodes(hasTestTag("userCardItem")).assertCountEquals(2)

        composeTestRule.onNode(hasText(searchTextFieldLabel)).performTextReplacement("")

        composeTestRule.onAllNodes(hasTestTag("userCardItem")).assertCountEquals(4)
    }
}