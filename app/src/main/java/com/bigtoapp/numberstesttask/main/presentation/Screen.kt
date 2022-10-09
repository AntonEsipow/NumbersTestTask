package com.bigtoapp.numberstesttask.main.presentation

import com.bigtoapp.numberstesttask.details.presentation.NumberDetailsFragment
import com.bigtoapp.numberstesttask.numbers.presentation.NumbersFragment

sealed class Screen {

    abstract fun fragment(): Class<out BaseFragment<*>>

    object Details : Screen() {
        override fun fragment(): Class<out BaseFragment<*>> = NumberDetailsFragment::class.java
    }

    object Numbers : Screen() {
        override fun fragment(): Class<out BaseFragment<*>> = NumbersFragment::class.java
    }
}
