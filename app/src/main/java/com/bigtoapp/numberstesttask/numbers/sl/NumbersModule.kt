package com.bigtoapp.numberstesttask.numbers.sl

import com.bigtoapp.numberstesttask.main.sl.Core
import com.bigtoapp.numberstesttask.main.sl.Module
import com.bigtoapp.numberstesttask.numbers.data.BaseNumbersRepository
import com.bigtoapp.numberstesttask.numbers.data.HandleDataRequest
import com.bigtoapp.numberstesttask.numbers.data.HandleDomainError
import com.bigtoapp.numberstesttask.numbers.data.NumberDataToDomain
import com.bigtoapp.numberstesttask.numbers.data.cache.NumbersCacheDataSource
import com.bigtoapp.numberstesttask.numbers.data.cache.NumbersDataToCache
import com.bigtoapp.numberstesttask.numbers.data.cloud.NumbersCloudDataSource
import com.bigtoapp.numberstesttask.numbers.data.cloud.NumbersService
import com.bigtoapp.numberstesttask.numbers.domain.HandleError
import com.bigtoapp.numberstesttask.numbers.domain.HandleRequest
import com.bigtoapp.numberstesttask.numbers.domain.NumberUiMapper
import com.bigtoapp.numberstesttask.numbers.domain.NumbersInteractor
import com.bigtoapp.numberstesttask.numbers.presentation.*

class NumbersModule(private val core: Core) : Module<NumbersViewModel> {

    override fun viewModel(): NumbersViewModel {
        val communications = NumbersCommunications.Base(
            ProgressCommunication.Base(),
            NumbersStateCommunication.Base(),
            NumbersListCommunication.Base()
        )
        val cacheDataSource = NumbersCacheDataSource.Base(
            core.provideDatabase().numbersDao(),
            NumbersDataToCache()
        )
        val repository = BaseNumbersRepository(
            NumbersCloudDataSource.Base(
                core.service(NumbersService::class.java)
            ),
            cacheDataSource,
            HandleDataRequest.Base(
                cacheDataSource,
                NumberDataToDomain(),
                HandleDomainError()
            ),
            NumberDataToDomain()
        )
        return NumbersViewModel(
            HandleNumbersRequest.Base(
                core.provideDispatchers(),
                communications,
                NumbersResultMapper(communications, NumberUiMapper())
            ),
            core,
            communications,
            NumbersInteractor.Base(
                repository,
                HandleRequest.Base(
                    HandleError.Base(core),
                    repository
                )
            )
        )
    }
}