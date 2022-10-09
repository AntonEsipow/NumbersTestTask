package com.bigtoapp.numberstesttask

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bigtoapp.numberstesttask.main.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest : BaseTest() {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun details_navigation() {
        val numbersPage = NumbersPage()
        numbersPage.run {
            input.view().typeText("10")
            getFactButton.view().click()
            recycler.viewInRecycler(0, titleItem).checkText("10")
            recycler.viewInRecycler(0, subTitleItem).checkText("fact about 10")
            recycler.viewInRecycler(0, subTitleItem).click()
        }

        DetailsPage().details.view().checkText("10\n\nfact about 10")
        pressBack()

        numbersPage.run {
            recycler.viewInRecycler(0, titleItem).checkText("10")
            recycler.viewInRecycler(0, subTitleItem).checkText("fact about 10")
        }
    }
}