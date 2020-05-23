package me.androidbox.presentation.pages

import com.agoda.kakao.screen.Screen.Companion.idle
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

    fun wait(duration: Long): InProgressPage = apply {
        idle(duration)
    }
}
