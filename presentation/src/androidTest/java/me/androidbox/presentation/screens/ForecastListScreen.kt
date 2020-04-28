package me.androidbox.presentation.screens

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import me.androidbox.presentation.R
import org.hamcrest.Matcher

class ForecastListScreen : Screen<ForecastListScreen>() {

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val weekDay: KTextView = KTextView(parent) { withId(R.id.tvWeekDay) }
        val weatherDescription: KTextView = KTextView(parent) { withId(R.id.tvWeatherDescription) }
        val highTemperature: KTextView = KTextView(parent) { withId(R.id.tvHighTemperature) }
        val lowTemperature: KTextView = KTextView(parent) { withId(R.id.tvLowTemperature) }
    }

    val dailyForecast : KRecyclerView =
        KRecyclerView( { withId(R.id.rvDailyForecast) }, { itemType(::Item) })
}