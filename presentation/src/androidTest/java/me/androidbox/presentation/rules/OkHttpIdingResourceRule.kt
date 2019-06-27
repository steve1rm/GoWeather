package me.androidbox.presentation.rules

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import javax.inject.Inject

class OkHttpIdingResourceRule @Inject constructor(val idlingResource: IdlingResource) : TestRule {

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
