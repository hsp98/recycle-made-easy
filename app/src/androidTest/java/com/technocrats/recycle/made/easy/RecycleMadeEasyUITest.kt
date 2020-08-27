package com.technocrats.recycle.made.easy

import android.util.Log
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.threeten.bp.YearMonth
import org.threeten.bp.format.DateTimeFormatter

@RunWith(AndroidJUnit4:: class)
class RecycleMadeEasyUITest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    //Checks if Calendar fragment is displayed on the screen on clicking calendar icon from bottom navigation
    @Test
    fun scrollToCalendarFragmentTest() {
        onView(ViewMatchers.withId(R.id.navigation_calendar)).perform(ViewActions.click())

        onView(ViewMatchers.withId(R.id.calendarFragment)).check(matches(ViewMatchers.isDisplayed()))
    }

    //Checks if Donate fragment is displayed on the screen on clicking donate icon from bottom navigation
    @Test
    fun scrollToDonateFragmentTest() {
        onView(ViewMatchers.withId(R.id.navigation_donate)).perform(ViewActions.click())

        onView(ViewMatchers.withId(R.id.donatefrag)).check(matches(ViewMatchers.isDisplayed()))
    }

    //Checks if Settings fragment is displayed on the screen on clicking settings icon from bottom navigation
    @Test
    fun scrollToSettingsFragmentTest() {
        onView(ViewMatchers.withId(R.id.navigation_settings)).perform(ViewActions.click())

        onView(ViewMatchers.withId(R.id.settingsFragment)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun scrollToCategoriesFragmentTest() {
        onView(ViewMatchers.withId(R.id.navigation_settings)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.navigation_categories)).perform(ViewActions.click())

        onView(ViewMatchers.withId(R.id.categoriesFragment)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun catergoryListDisplayTest() {
        onView(ViewMatchers.withId(R.id.navigation_categories)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.recyclerView)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun categoryListDataTest() {
//        onView(ViewMatchers.withId(R.id.recyclerView))
//            .inRoot(RootMatchers.withDecorView(Matchers.is(activityRule.activity.window.decorView))).perform(RecyclerViewAction)
    }

    // Checks the current month year on calendar display
    @Test
    fun currentMonthYearDisplayTest() {
        val time = YearMonth.now()
        val month = time.month.toString().toLowerCase().capitalize()
        val expectedMonthYear = "${month} ${time.year}"
        Log.d("currentMonthYear", "" + expectedMonthYear)
        onView(ViewMatchers.withId(R.id.navigation_calendar)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.calendarMonthYearText)).check(ViewAssertions.matches(ViewMatchers.withText(expectedMonthYear)))
    }

}


