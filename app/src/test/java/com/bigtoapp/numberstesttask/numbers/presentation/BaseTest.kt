package com.bigtoapp.numberstesttask.numbers.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.bigtoapp.numberstesttask.main.presentation.NavigationCommunication
import com.bigtoapp.numberstesttask.main.presentation.NavigationStrategy

abstract class BaseTest {

    protected class TestNavigationCommunication : NavigationCommunication.Mutable {

        lateinit var strategy: NavigationStrategy
        var count = 0
        override fun observe(owner: LifecycleOwner, observer: Observer<NavigationStrategy>) =Unit

        override fun map(source: NavigationStrategy) {
            strategy = source
            count++
        }
    }

    protected class TestNumbersCommunications : NumbersCommunications {

        val progressCalledList = mutableListOf<Int>()
        val stateCalledList = mutableListOf<UiState>()
        var timesShowList = 0
        val numbersList = mutableListOf<NumberUi>()

        override fun showProgress(show: Int) {
            progressCalledList.add(show)
        }

        override fun showState(uiState: UiState) {
            stateCalledList.add(uiState)
        }

        override fun showList(list: List<NumberUi>) {
            timesShowList++
            numbersList.addAll(list)
        }
    }
}