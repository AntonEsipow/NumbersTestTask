package com.bigtoapp.numberstesttask.numbers.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface NumbersCommunications : ObserveNumbers {

    fun showProgress(show: Int)

    fun showState(uiState: UiState)

    fun showList(list: List<NumberUi>)

    class Base(
        private val progress: ProgressCommunication,
        private val state: NumbersStateCommunication,
        private val numbersList: NumbersListCommunication
    ) : NumbersCommunications {

        override fun showProgress(show: Int) = progress.map(show)

        override fun showState(uiState: UiState) = state.map(uiState)

        override fun showList(list: List<NumberUi>) = numbersList.map(list)

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) =
            progress.observe(owner, observer)

        override fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) =
            state.observe(owner, observer)

        override fun observeList(owner: LifecycleOwner, observer: Observer<List<NumberUi>>) =
            numbersList.observe(owner, observer)
    }
}

interface ObserveNumbers {

    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) = Unit

    fun observeState(owner: LifecycleOwner, observer: Observer<UiState>) = Unit

    fun observeList(owner: LifecycleOwner, observer: Observer<List<NumberUi>>) = Unit
}

interface ProgressCommunication : Communication.Mutable<Int> {
    class Base : Communication.Ui<Int>(), ProgressCommunication
}

interface NumbersStateCommunication : Communication.Mutable<UiState> {
    class Base : Communication.Ui<UiState>(), NumbersStateCommunication
}

interface NumbersListCommunication : Communication.Mutable<List<NumberUi>> {
    class Base : Communication.Ui<List<NumberUi>>(), NumbersListCommunication
}