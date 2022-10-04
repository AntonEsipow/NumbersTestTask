package com.bigtoapp.numberstesttask.numbers.data

import com.bigtoapp.numberstesttask.numbers.data.cache.NumbersCacheDataSource
import com.bigtoapp.numberstesttask.numbers.domain.HandleError
import com.bigtoapp.numberstesttask.numbers.domain.NumberFact

interface HandleDataRequest {

    suspend fun handle(block: suspend () -> NumberData): NumberFact

    class Base(
        private val cacheDataSource: NumbersCacheDataSource,
        private val mapperToDomain: NumberData.Mapper<NumberFact>,
        private val handleError: HandleError<Exception>
    ): HandleDataRequest {

        override suspend fun handle(block: suspend () -> NumberData) = try {
            val result = block.invoke()
            cacheDataSource.saveNumber(result)
            result.map(mapperToDomain)
        } catch(e: Exception) {
            throw  handleError.handle(e)
        }
    }
}