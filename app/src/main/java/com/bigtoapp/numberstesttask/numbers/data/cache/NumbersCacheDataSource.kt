package com.bigtoapp.numberstesttask.numbers.data.cache

import com.bigtoapp.numberstesttask.numbers.data.NumberData

interface NumbersCacheDataSource: FetchNumber{

    suspend fun allNumbers(): List<NumberData>

    suspend fun contains(number: String): Boolean

    suspend fun saveNumber(numberData: NumberData)
}

interface FetchNumber {
    suspend fun number(string: String): NumberData
}