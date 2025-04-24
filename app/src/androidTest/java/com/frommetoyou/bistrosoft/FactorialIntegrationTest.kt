package com.frommetoyou.bistrosoft

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class FactorialIntegrationTest: AppAndroidTest() {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testFactorialCalculationIsDone() {

        composeRule.onNodeWithText("Factorial de…")
            .performClick()
            .performTextInput("12")

        composeRule.onNodeWithText("Calcular")
            .performClick()

        composeRule.onNodeWithTag("FactorialResult")
            .assertTextEquals("479001600")

        resetInput()

        composeRule.onNodeWithText("Factorial de…")
            .performClick()
            .performTextInput("3")

        composeRule.onNodeWithText("Calcular")
            .performClick()

        composeRule.onNodeWithTag("FactorialResult")
            .assertTextEquals("6")

        resetInput()
        composeRule.onNodeWithText("Factorial de…")
            .performClick()
            .performTextInput("4")

        composeRule.onNodeWithText("Calcular")
            .performClick()

        composeRule.onNodeWithTag("FactorialResult")
            .assertTextEquals("24")
        resetInput()

        composeRule.onNodeWithText("Factorial de…")
            .performClick()
            .performTextInput("3")

        composeRule.onNodeWithText("Calcular")
            .performClick()

        composeRule.onNodeWithTag("FactorialResult")
            .assertTextEquals("6")

        resetInput()
        composeRule.onNodeWithText("Factorial de…")
            .performClick()
            .performTextInput("2")

        composeRule.onNodeWithText("Calcular")
            .performClick()

        composeRule.onNodeWithTag("FactorialResult")
            .assertTextEquals("2")

        resetInput()
        composeRule.onNodeWithText("Factorial de…")
            .performClick()
            .performTextInput("5")

        composeRule.onNodeWithText("Calcular")
            .performClick()

        composeRule.onNodeWithTag("FactorialResult")
            .assertTextEquals("120")
    }

    private fun resetInput() {
        composeRule.onNodeWithText("Factorial de…")
            .performClick()
            .performTextClearance()
    }
}