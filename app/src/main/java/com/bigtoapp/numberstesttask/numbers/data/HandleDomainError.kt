package com.bigtoapp.numberstesttask.numbers.data

import com.bigtoapp.numberstesttask.numbers.domain.HandleError
import com.bigtoapp.numberstesttask.numbers.domain.NoInternetConnectionException
import com.bigtoapp.numberstesttask.numbers.domain.ServiceUnavailableException
import java.net.UnknownHostException

class HandleDomainError: HandleError<Exception> {

    override fun handle(e: Exception) = when(e) {
        is UnknownHostException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }
}