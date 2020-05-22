package me.androidbox.presentation.pages

import com.agoda.kakao.screen.Screen.Companion.idle
import me.androidbox.presentation.R
import me.androidbox.presentation.screens.RetryFailureScreen

object RetryFailurePage {

    private val retryFailureScreen: RetryFailureScreen by lazy {
        RetryFailureScreen()
    }

    fun shouldBeVisible(): RetryFailurePage = apply {
        retryFailureScreen {
            container {
                isDisplayed()
            }
        }
    }

    fun shouldDisplayFailureMessge(): RetryFailurePage = apply {
        retryFailureScreen {
            failureMessage {
                isDisplayed()
                hasText(R.string.failure_message)
            }
        }
    }

    fun shouldDisplayRetryButton(): RetryFailurePage = apply {
        retryFailureScreen {
            retryButton {
                isDisplayed()
                hasText(R.string.retry)
            }
        }
    }

    fun tapRetryButton(): RetryFailurePage = apply {
        retryFailureScreen {
            retryButton {
                perform {
                    click()
                }
            }
        }
    }

    fun waitIdle(): RetryFailurePage = apply {
        retryFailureScreen {
            idle()
        }
    }
}