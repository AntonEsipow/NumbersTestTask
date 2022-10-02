package com.bigtoapp.numberstesttask.main

import android.app.Application
import com.bigtoapp.numberstesttask.BuildConfig
import com.bigtoapp.numberstesttask.numbers.data.cloud.CloudModule

class NumbersApp: Application() {

    override fun onCreate() {
        super.onCreate()

        //todo move out of here
        val cloudModule = if (BuildConfig.DEBUG)
            CloudModule.Debug()
        else
            CloudModule.Release()
    }
}