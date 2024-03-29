package com.bigtoapp.numberstesttask.numbers.domain

import com.bigtoapp.numberstesttask.numbers.presentation.NumberUi

sealed class NumbersResult {

    interface Mapper<T: Any> {
        fun map(list: List<NumberFact>): T
        fun map(errorMessage: String): T
    }

    abstract fun <T: Any> map(mapper: Mapper<T>) : T

    data class Success(private val list: List<NumberFact> = emptyList()): NumbersResult() {
        override fun <T: Any> map(mapper: Mapper<T>): T = mapper.map(list)
    }

    data class Failure(private val message: String): NumbersResult() {
        override fun <T: Any> map(mapper: Mapper<T>): T = mapper.map(message)
    }
}