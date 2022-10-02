package com.bigtoapp.numberstesttask.numbers.data

import com.bigtoapp.numberstesttask.numbers.domain.NumberFact

class NumberDataToDomain: NumberData.Mapper<NumberFact> {
    override fun map(id: String, fact: String) = NumberFact(id, fact)
}