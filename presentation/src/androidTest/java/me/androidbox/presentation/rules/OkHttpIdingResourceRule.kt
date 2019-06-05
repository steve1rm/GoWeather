package me.androidbox.presentation.rules

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.android.AndroidInjection
import okhttp3.OkHttpClient
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class OkHttpIdingResourceRule(okHttpClient: OkHttpClient) : TestRule {

    private val idlingResource: IdlingResource = OkHttp3IdlingResource.create("okhttp", okHttpClient)

    init {
        
    }

    override fun apply(base: Statement?, description: Description?): Statement {
        return object: Statement() {
            override fun evaluate() {
                IdlingRegistry.getInstance().register(idlingResource)
                base?.evaluate()
                IdlingRegistry.getInstance().unregister(idlingResource)
            }
        }
    }
}
