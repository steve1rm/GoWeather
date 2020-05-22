package me.androidbox.presentation.screens

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import me.androidbox.presentation.R

class InProgressScreen : Screen<InProgressScreen>() {

    val container: KView = KView { withId(R.id.inprogress) }

    val progress: KImageView = KImageView { withId(R.id.ivProgress) }
}
