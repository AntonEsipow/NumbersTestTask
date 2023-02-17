package com.bigtoapp.numberstesttask.main.sl

import com.bigtoapp.numberstesttask.main.presentation.MainViewModel

class MainModule(private val core: Core) : Module<MainViewModel> {

    override fun viewModel() = MainViewModel(
        core.provideNavigation(),
        core.provideWorkManagerWrapper()
    )
}