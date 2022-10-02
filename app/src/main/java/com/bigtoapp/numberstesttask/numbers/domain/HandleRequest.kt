package com.bigtoapp.numberstesttask.numbers.domain

interface HandleRequest {

    suspend fun handle(block: suspend () -> Unit): NumbersResult

    class Base(
        private val handleError: HandleError<String>,
        private val repository: NumbersRepository
    ): HandleRequest {

        override suspend fun handle(block: suspend () -> Unit) = try {
            block.invoke()
            NumbersResult.Success(repository.allNumbers())
        } catch (e: Exception) {
            NumbersResult.Failure(handleError.handle(e))
        }
    }
}