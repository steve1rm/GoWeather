package me.androidbox.presentation.viewAssertions

import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun childAtPosition(parentMatcher: Matcher<View>, positionAt: Int): Matcher<View> {
    return object: TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("positionAt $positionAt")
            parentMatcher.describeMismatch(positionAt, description)
        }

        override fun matchesSafely(view: View): Boolean {
            return if(view.parent !is ViewGroup) {
                false
            }
            else {
                val parent: ViewGroup = view.parent as ViewGroup
                parentMatcher.matches(parent) &&
                        parent.childCount > positionAt &&
                        parent.getChildAt(positionAt) == view
            }
        }
    }
}
