package br.com.havebreak

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId

abstract class ScreenRobot<T> {

    fun checkViewIsVisible(viewId: Int): T {
        onView(withId(viewId)).check(matches(isDisplayed()))

        return this as T
    }

    fun clickOnView(viewId: Int): T {
        onView(withId(viewId)).perform(click())

        return this as T
    }

    fun enterTextIntoView(viewId: Int, text:String) {
        onView(withId(viewId)).perform(typeText(text))
    }

}