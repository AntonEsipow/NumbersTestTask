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
        val mapper = NumbersResultMapper(communications, NumberUiMapper())
        val interactor = NumbersInteractor.Base(
            repository,
            HandleRequest.Base(
                HandleError.Base(core),
                repository
            ),
            core.provideNumberDetails()
        )
        return NumbersViewModel.Base(
            core.provideDispatchers(),
            NumbersInitialFeature(communications, mapper, interactor),
            NumbersFactFeature.Base(core, communications, mapper, interactor),
            RandomNumberFactFeature(interactor, communications, mapper),
            ShowDetails.Base(interactor, core.provideNavigation(), DetailsUi()),
            communications
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