package com.bigtoapp.numberstesttask.main.sl

import android.content.Context
import com.bigtoapp.numberstesttask.numbers.data.cache.CacheModule
import com.bigtoapp.numberstesttask.numbers.data.cloud.CloudModule
import com.bigtoapp.numberstesttask.numbers.data.cloud.RandomApiHeader

interface ProvideInstances: ProvideRandomApiHeader {

    fun provideCloudModule() : CloudModule
    fun provideCacheModule() : CacheModule

    class Realise(private val context: Context) : ProvideInstances {
        override fun provideCloudModule(): CloudModule = CloudModule.Base()
        override fun provideCacheModule(): CacheModule = CacheModule.Base(context)
        override fun provideRandomApiHeader(): RandomApiHeader.Combo = RandomApiHeader.Base()
    }

    class Mock(private val context: Context) : ProvideInstances {
        override fun provideCloudModule(): CloudModule = CloudModule.Mock(provideRandomApiHeader())
        override fun provideCacheModule(): CacheModule = CacheModule.Mock(context)
        override fun provideRandomApiHeader(): RandomApiHeader.Combo = RandomApiHeader.Mock()
    }
}

interface ProvideRandomApiHeader {
    fun provideRandomApiHeader() : RandomApiHeader.Combo
}