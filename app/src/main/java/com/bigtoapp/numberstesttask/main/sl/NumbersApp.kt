package com.bigtoapp.numberstesttask.main.sl

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.bigtoapp.numberstesttask.BuildConfig
import com.bigtoapp.numberstesttask.numbers.data.cloud.CloudModule

class NumbersApp: Application(), ProvideViewModel {

    private lateinit var viewModelsFactory: ViewModelsFactory

    override fun onCreate() {
        super.onCreate()
        viewModelsFactory = ViewModelsFactory(
            DependencyContainer.Base(Core.Base(this, !BuildConfig.DEBUG))
        )
    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, viewModelsFactory)[clazz]
}