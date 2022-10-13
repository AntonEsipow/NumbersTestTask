package com.bigtoapp.numberstesttask.numbers.data.cloud

import okhttp3.Headers.Companion.toHeaders
import retrofit2.Response

class MockNumbersService(
    private val randomApiHeader: RandomApiHeader.MockResponse
) : NumbersService {

    private var count = 0

    override suspend fun random() = (++count).toString().let {
        randomApiHeader.makeResponse(fact(it), it)
    }

    override suspend fun fact(id: String) = "fact about $id"
}