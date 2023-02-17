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
import com.bigtoapp.numberstesttask.numbers.domain.*
import com.bigtoapp.numberstesttask.numbers.presentation.*
import com.bigtoapp.numberstesttask.random.ProvidePeriodicRepository

class NumbersModule(
    private val core: Core,
    private val provideRepository: ProvideNumbersRepository
    ) : Module<NumbersViewModel.Base> {

    override fun viewModel(): NumbersViewModel.Base {
        val repository = provideRepository.provideNumbersRepository()
        val communications = NumbersCommunications.Base(
            ProgressCommunication.Base(),
            NumbersStateCommunication.Base(),
            NumbersListCommunication.Base()
        )
        return NumbersViewModel.Base(
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
                ),
                core.provideNumberDetails()
            ),
            core.provideNavigation(),
            DetailsUi()
        )
    }
}

interface ProvideNumbersRepository{

    fun provideNumbersRepository(): NumbersRepository

    class Base(private val core: Core): ProvideNumbersRepository {

        override fun provideNumbersRepository(): NumbersRepository {
            val cacheDataSource = NumbersCacheDataSource.Base(
                core.provideDatabase().numbersDao(),
                NumbersDataToCache()
            )
            return BaseNumbersRepository(
                NumbersCloudDataSource.Base(
                    core.service(NumbersService::class.java),
                    core.provideRandomApiHeader()
                ),
                cacheDataSource,
                HandleDataRequest.Base(
                    cacheDataSource,
                    NumberDataToDomain(),
                    HandleDomainError()
                ),
                NumberDataToDomain()
            )
        }
    }
}