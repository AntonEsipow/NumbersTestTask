package com.bigtoapp.numberstesttask.details.sl

import com.bigtoapp.numberstesttask.details.presentation.NumberDetailsViewModel
import com.bigtoapp.numberstesttask.main.sl.Module
import com.bigtoapp.numberstesttask.main.sl.ProvideNumberDetails

class NumberDetailsModule(
    private val provideNumberDetails: ProvideNumberDetails
) : Module<NumberDetailsViewModel> {

    override fun viewModel() = NumberDetailsViewModel(provideNumberDetails.provideNumberDetails())
}