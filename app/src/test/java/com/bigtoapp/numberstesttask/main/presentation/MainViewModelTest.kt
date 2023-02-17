package com.bigtoapp.numberstesttask.main.presentation

import com.bigtoapp.numberstesttask.numbers.presentation.BaseTest
import com.bigtoapp.numberstesttask.random.WorkManagerWrapper
import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelTest : BaseTest() {

    @Test
    fun `test navigation at start`() {
        val navigation = TestNavigationCommunication()
        val workManager = TestWorkManagerWrapper()
        val mainViewModel = MainViewModel(navigation, workManager)

        mainViewModel.init(true)
        assertEquals(1, navigation.count)
        assertEquals(NavigationStrategy.Replace(Screen.Numbers), navigation.strategy)
        assertEquals(1, workManager.startCalledCount)

        mainViewModel.init(false)
        assertEquals(1, navigation.count)
        assertEquals(1, workManager.startCalledCount)
    }

    private class TestWorkManagerWrapper: WorkManagerWrapper {

        var startCalledCount = 0

        override fun start() {
            startCalledCount++
        }
    }
}