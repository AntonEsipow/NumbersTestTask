package com.bigtoapp.numberstesttask.details.presentation

import androidx.lifecycle.ViewModel
import com.bigtoapp.numberstesttask.details.data.NumberFactDetails

class NumberDetailsViewModel(
    private val data: NumberFactDetails.Read
) : ViewModel(), NumberFactDetails.Read {

    override fun read(): String = data.read()
}