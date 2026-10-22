package com.example.lab7

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DriversTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun navigate_from_home_to_drivers_screen() {
        composeTestRule.onNodeWithTag("driversButton").performClick()

        composeTestRule.onNodeWithText("FormulaZero Drivers").assertExists()
    }

    @Test
    fun add_driver_and_verify_displayed() {
        composeTestRule.onNodeWithTag("driversButton").performClick()

        composeTestRule.onNodeWithTag("driverInput").performTextInput("Hamilton")
        composeTestRule.onNodeWithTag("addDriverButton").performClick()

        composeTestRule.onNodeWithText("Hamilton").assertExists()
    }

    @Test
    fun clear_drivers_removes_all() {
        composeTestRule.onNodeWithTag("driversButton").performClick()

        // add two drivers
        composeTestRule.onNodeWithTag("driverInput").performTextInput("Hamilton")
        composeTestRule.onNodeWithTag("addDriverButton").performClick()
        composeTestRule.onNodeWithTag("driverInput").performTextInput("Verstappen")
        composeTestRule.onNodeWithTag("addDriverButton").performClick()

        composeTestRule.onNodeWithText("Hamilton").assertExists()
        composeTestRule.onNodeWithText("Verstappen").assertExists()

        // clear them
        composeTestRule.onNodeWithTag("clearDriversButton").performClick()

        composeTestRule.onNodeWithText("Hamilton").assertDoesNotExist()
        composeTestRule.onNodeWithText("Verstappen").assertDoesNotExist()
    }

    @Test
    fun back_button_returns_to_home() {
        composeTestRule.onNodeWithTag("driversButton").performClick()
        composeTestRule.onNodeWithTag("driversBackButton").performClick()

        composeTestRule.onNodeWithText("FormulaZero Home").assertExists()
    }
}