package com.bigtoapp.numberstesttask.main.sl

import androidx.lifecycle.ViewModel
import com.bigtoapp.numberstesttask.details.presentation.NumberDetailsViewModel
import com.bigtoapp.numberstesttask.details.sl.NumberDetailsModule
import com.bigtoapp.numberstesttask.main.presentation.MainViewModel
import com.bigtoapp.numberstesttask.numbers.presentation.NumbersViewModel
import com.bigtoapp.numberstesttask.numbers.sl.NumbersModule
import java.lang.IllegalStateException

interface DependencyContainer {

    fun <T: ViewModel> module(clazz: Class<T>) : Module<*>

    class Error() : DependencyContainer {
        override fun <T : ViewModel> module(clazz: Class<T>): Module<*> =
            throw IllegalStateException("no module found for $clazz")
    }

    class Base(
        private val core: Core,
        private val dependencyContainer: DependencyContainer = Error()
    ) : DependencyContainer {

        override fun <T : ViewModel> module(clasz: Class<T>): Module<*> = when (clasz) {
            MainViewModel::class.java -> MainModule(core)
            NumbersViewModel.Base::class.java -> NumbersModule(core)
            NumberDetailsViewModel::class.java -> NumberDetailsModule(core)
            else -> dependencyContainer.module(clasz)
        }

    }
}