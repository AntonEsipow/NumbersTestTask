package com.bigtoapp.numberstesttask.numbers.presentation

import com.bigtoapp.numberstesttask.numbers.domain.NumbersInitialUseCase
import com.bigtoapp.numberstesttask.numbers.domain.NumbersResult

class NumbersInitialFeature(
    communications: NumbersCommunications,
    numbersResultMapper: NumbersResult.Mapper<Unit>,
    private val useCase: NumbersInitialUseCase
) : NumbersFeature(communications, numbersResultMapper) {

    override suspend fun invoke() = useCase.init()
}