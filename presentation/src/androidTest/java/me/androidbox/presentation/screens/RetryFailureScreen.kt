package me.androidbox.presentation.screens

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import me.androidbox.presentation.R

class RetryFailureScreen : Screen<RetryFailureScreen>() {

    val container: KView = KView { withId(R.id.failurecase_layout)}

    val failureMessage: KTextView = KTextView { withId(R.id.tvFailureMessage) }

    val retryButton: KButton = KButton { withId(R.id.btnRetry) }
}
