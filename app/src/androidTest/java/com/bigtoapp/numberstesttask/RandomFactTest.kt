package com.bigtoapp.numberstesttask

import org.junit.Test

class RandomFactTest: BaseTest() {

    @Test
    fun test() = NumbersPage().run {
        randomButton.view().click()
        recycler.run {
            viewInRecycler(0, titleItem).checkText("1")
            viewInRecycler(0, subTitleItem).checkText("fact about 1")
        }
        randomButton.view().click()
        recycler.run {
            viewInRecycler(0, titleItem).checkText("2")
            viewInRecycler(0, subTitleItem).checkText("fact about 2")
            viewInRecycler(1, titleItem).checkText("1")
            viewInRecycler(1, subTitleItem).checkText("fact about 1")
        }
    }
}