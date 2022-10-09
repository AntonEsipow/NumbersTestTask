package com.bigtoapp.numberstesttask

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bigtoapp.numberstesttask.main.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class CheckItemReplacedTest : BaseTest() {

    @Test
    fun test_history() {
        NumbersPage().run{
            input.view().typeText("1")
            getFactButton.view().click()
            recycler.viewInRecycler(0, titleItem).checkText("1")
            recycler.viewInRecycler(0, subTitleItem).checkText("fact about 1")

            input.view().typeText("2")
            getFactButton.view().click()
            recycler.viewInRecycler(0, titleItem).checkText("2")
            recycler.viewInRecycler(0, subTitleItem).checkText("fact about 2")
            recycler.viewInRecycler(1, titleItem).checkText("1")
            recycler.viewInRecycler(1, subTitleItem).checkText("fact about 1")

            input.view().typeText("1")
            getFactButton.view().click()
            recycler.viewInRecycler(0, titleItem).checkText("1")
            recycler.viewInRecycler(0, subTitleItem).checkText("fact about 1")
            recycler.viewInRecycler(1, titleItem).checkText("2")
            recycler.viewInRecycler(1, subTitleItem).checkText("fact about 2")
        }
    }
}