package com.bigtoapp.numberstesttask.main.sl

import androidx.lifecycle.ViewModel
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

        override fun <T : ViewModel> module(clazz: Class<T>): Module<*> =
            if (clazz == NumbersViewModel::class.java)
                NumbersModule(core)
            else
                dependencyContainer.module(clazz)

    }
}