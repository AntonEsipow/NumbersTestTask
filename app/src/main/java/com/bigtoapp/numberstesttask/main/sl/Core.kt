package com.bigtoapp.numberstesttask.main.sl

import android.content.Context
import com.bigtoapp.numberstesttask.numbers.data.cache.CacheModule
import com.bigtoapp.numberstesttask.numbers.data.cache.NumbersRoomDatabase
import com.bigtoapp.numberstesttask.numbers.data.cloud.CloudModule
import com.bigtoapp.numberstesttask.numbers.presentation.DispatchersList
import com.bigtoapp.numberstesttask.numbers.presentation.ManageResources

interface Core : CloudModule, CacheModule, ManageResources {

    fun provideDispatchers(): DispatchersList

    class Base(
        context: Context,
        private val provideInstances: ProvideInstances
    ) : Core {

        private val manageResources: ManageResources = ManageResources.Base(context)

        private val dispatchersList by lazy {
            DispatchersList.Base()
        }
        private val cloudModule by lazy {
            provideInstances.provideCloudModule()
        }

        private val cacheModule by lazy {
            provideInstances.provideCacheModule()
        }

        override fun <T> service(clasz: Class<T>): T = cloudModule.service(clasz)

        override fun provideDatabase() = cacheModule.provideDatabase()

        override fun string(id: Int) = manageResources.string(id)

        override fun provideDispatchers() = dispatchersList

    }
}