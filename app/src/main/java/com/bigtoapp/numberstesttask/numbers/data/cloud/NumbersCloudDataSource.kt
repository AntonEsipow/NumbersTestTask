package com.bigtoapp.numberstesttask.numbers.data.cloud

import com.bigtoapp.numberstesttask.numbers.data.FetchNumber
import com.bigtoapp.numberstesttask.numbers.data.NumberData

interface NumbersCloudDataSource: FetchNumber {

    suspend fun randomNumber(): NumberData

    class Base(private val service: NumbersService) : NumbersCloudDataSource {

        override suspend fun randomNumber(): NumberData {
            val response = service.random()
            val body = response.body() ?: throw IllegalStateException("service unavailable")
            val headers = response.headers()
            headers.find { (key, _) ->
                key == RANDOM_API_HEADER
            }?.let { (_, value) ->
                return NumberData(value, body)
            }
            throw IllegalStateException("service unavailable")
        }

        override suspend fun number(number: String): NumberData {
            val fact = service.fact(number)
            return NumberData(number, fact)
        }

        companion object {
            private const val RANDOM_API_HEADER = "X-Numbers-API-Number"
        }
    }
}