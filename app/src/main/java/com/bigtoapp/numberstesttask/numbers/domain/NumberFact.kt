package com.bigtoapp.numberstesttask.numbers.domain

data class NumberFact(
    private val id: String,
    private val fact: String
) {
    interface Mapper<T: Any> {
        fun map(id: String, fact: String): T
    }

    fun <T: Any> map(mapper: Mapper<T>): T = mapper.map(id, fact)
}