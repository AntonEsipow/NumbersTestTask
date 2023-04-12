package com.bigtoapp.numberstesttask.numbers.domain

import com.bigtoapp.numberstesttask.R
import com.bigtoapp.numberstesttask.numbers.presentation.ManageResources

interface HandleError<T: Any> {

    fun handle(e: Exception): T

    class Base(private val manageResources: ManageResources): HandleError<String> {

        override fun handle(e: Exception): String = manageResources.string(
            when (e) {
                is NoInternetConnectionException -> R.string.no_connection_message
                else -> R.string.service_is_unavailable
            }
        )
    }
}