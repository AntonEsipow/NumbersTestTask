package com.bigtoapp.numberstesttask.numbers.presentation

interface Mapper<R: Any, S: Any> {

    fun map(source: S): R

    interface Unit<S: Any> : Mapper<kotlin.Unit, S>
}