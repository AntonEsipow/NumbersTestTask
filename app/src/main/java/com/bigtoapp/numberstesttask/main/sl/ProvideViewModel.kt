package com.bigtoapp.numberstesttask.main.sl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner

interface ProvideViewModel {
    fun <T: ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T
}