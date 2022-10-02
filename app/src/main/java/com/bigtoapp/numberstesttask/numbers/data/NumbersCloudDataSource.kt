package com.bigtoapp.numberstesttask.numbers.data

interface NumbersCloudDataSource: FetchNumber {

    suspend fun randomNumber(): NumberData
}