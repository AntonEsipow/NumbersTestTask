package com.bigtoapp.numberstesttask.main.sl

import com.bigtoapp.numberstesttask.main.presentation.MainViewModel

class MainModule(private val provideNavigation: ProvideNavigation) : Module<MainViewModel> {

    override fun viewModel() = MainViewModel(provideNavigation.provideNavigation())
}