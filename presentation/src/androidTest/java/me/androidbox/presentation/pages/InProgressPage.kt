package me.androidbox.presentation.pages

import me.androidbox.presentation.screens.InProgressScreen

object InProgressPage {

    private val inProgressScreen: InProgressScreen by lazy {
        InProgressScreen()
    }

    fun shouldBeVisible(): InProgressPage = apply {
        inProgressScreen {
            container {
                isDisplayed()
            }
        }
    }

    fun shouldDisplayProgress(): InProgressPage = apply {
        inProgressScreen {
            progress {
                isDisplayed()
            }
        }
    }
}
