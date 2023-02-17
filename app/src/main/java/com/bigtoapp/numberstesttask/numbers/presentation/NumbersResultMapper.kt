package com.bigtoapp.numberstesttask.numbers.presentation

import com.bigtoapp.numberstesttask.numbers.domain.NumberFact
import com.bigtoapp.numberstesttask.numbers.domain.NumbersResult

class NumbersResultMapper(
    private val communications: NumbersCommunications,
    private val mapper: NumberFact.Mapper<NumberUi>
) : NumbersResult.Mapper<Unit> {

    override fun map(list: List<NumberFact>) {
        if (list.isNotEmpty())
            communications.showList(list.map { it.map(mapper) })
        communications.showState(UiState.Success())
    }

    override fun map(errorMessage: String) =
        communications.showState(UiState.ShowError(errorMessage))
}