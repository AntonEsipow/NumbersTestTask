package com.bigtoapp.numberstesttask.numbers.presentation

import com.bigtoapp.numberstesttask.numbers.domain.NumbersResult
import com.bigtoapp.numberstesttask.numbers.domain.RandomNumbersFactUseCase

class RandomNumberFactFeature(
    private val useCase: RandomNumbersFactUseCase,
    communications: NumbersCommunications,
    numbersResultMapper: NumbersResult.Mapper<Unit>,
) : NumbersFeature(communications, numbersResultMapper) {

    override suspend fun invoke() = useCase.factAboutRandomNumber()
}