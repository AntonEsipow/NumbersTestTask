package com.bigtoapp.numberstesttask.numbers.data.cloud

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

interface CloudModule {

    fun <T> service(clasz: Class<T>): T

    abstract class Abstract : CloudModule {

        protected abstract val level: HttpLoggingInterceptor.Level
        protected open val baseUrl: String = "http://numbersapi.com/"

        override fun <T> service(clasz: Class<T>): T {
            //todo refactor when service locator
            val interceptor = HttpLoggingInterceptor().apply {
                setLevel(level)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
            return retrofit.create(clasz)
        }
    }

    class Debug : Abstract() {
        override val level = HttpLoggingInterceptor.Level.BODY
    }

    class Release : Abstract() {
        override val level = HttpLoggingInterceptor.Level.NONE
    }
}