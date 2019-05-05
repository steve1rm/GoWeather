package me.androidbox.presentation.forecast

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoadingFragmentTest {

    private lateinit var loadingFragment: LoadingFragment

    @Before
    fun setUp() {
        loadingFragment = LoadingFragment()
        assertThat(loadingFragment).isNotNull

        
    }


}
