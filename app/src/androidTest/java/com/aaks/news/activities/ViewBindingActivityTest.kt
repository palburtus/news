package com.aaks.news.activities


import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.rule.ActivityTestRule
import com.aaks.news.R
import kotlinx.android.synthetic.main.activity_view_binding.view.*


/*
    Created by Damjan Miloshevski at 06.4.2020 10:53
    Project: news
    © Internet Brands 2020
*/

class ViewBindingActivityTest {
    @get:Rule
    val mActivityRule: ActivityTestRule<ViewBindingActivity> =
        ActivityTestRule(ViewBindingActivity::class.java)

    @Test
    fun editText_textChanges_works() {
        //Type text and then press the button
        onView(withId(R.id.inputField))
            .perform(typeText("HELLO"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.changeText)).perform(click())
        //Check that the text was changed
        onView(withId(R.id.inputField)).check(matches(withText("ViewBinding rocks!!")))

    }

    @Test
    fun changeText_newActivityOpened_works() {
        //Type text and then press the button
        onView(withId(R.id.inputField)).perform(
            typeText("New Text"),
            ViewActions.closeSoftKeyboard()
        )
        onView(withId(R.id.switchActivity)).perform(click())
        // This view is in a different Activity, no need to tell Espresso.
        onView(withId(R.id.resultView)).check(matches(withText("New Text")))
    }
}