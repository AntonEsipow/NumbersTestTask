package com.bigtoapp.numberstesttask.main.sl

import android.content.Context
import com.bigtoapp.numberstesttask.numbers.data.cache.CacheModule
import com.bigtoapp.numberstesttask.numbers.data.cloud.CloudModule

interface ProvideInstances {

    fun provideCloudModule() : CloudModule
    fun provideCacheModule() : CacheModule

    class Realise(private val context: Context) : ProvideInstances {
        override fun provideCloudModule(): CloudModule = CloudModule.Base()
        override fun provideCacheModule(): CacheModule = CacheModule.Base(context)
    }

    class Mock(private val context: Context) : ProvideInstances {
        override fun provideCloudModule(): CloudModule = CloudModule.Mock()
        override fun provideCacheModule(): CacheModule = CacheModule.Mock(context)
    }
}