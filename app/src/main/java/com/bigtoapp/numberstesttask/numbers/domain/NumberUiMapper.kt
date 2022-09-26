package com.bigtoapp.numberstesttask.numbers.domain

import com.bigtoapp.numberstesttask.numbers.presentation.NumberUi

class NumberUiMapper: NumberFact.Mapper<NumberUi> {

    override fun map(id: String, fact: String): NumberUi = NumberUi(id, fact)
}