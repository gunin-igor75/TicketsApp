package com.github.gunin_igor75.di

import com.github.gunin_igor75.data.repository.OffersRepositoryImpl
import com.github.gunin_igor75.data.repository.TicketsOffersRepositoryImp
import com.github.gunin_igor75.data.repository.TicketsRepositoryImpl
import com.github.gunin_igor75.domain.model.Offer
import com.github.gunin_igor75.domain.model.Ticket
import com.github.gunin_igor75.domain.model.TicketsOffers
import com.github.gunin_igor75.domain.repository.OffersRepository
import com.github.gunin_igor75.domain.repository.TicketOffersRepository
import com.github.gunin_igor75.domain.repository.TicketsRepository
import com.github.gunin_igor75.domain.usecase.GetOffers
import com.github.gunin_igor75.domain.usecase.GetTickets
import com.github.gunin_igor75.domain.usecase.GetTicketsOffers
import com.github.gunin_igor75.domain.usecase.ReadCityState
import com.github.gunin_igor75.domain.usecase.SaveCityState
import com.github.gunin_igor75.presentation.screens.offers.OffersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val dataModule = module {
    single<OffersRepository<List<Offer>>> {
        OffersRepositoryImpl(
            localSource = get(),
            networkSource = get()
        )
    }
    single<TicketOffersRepository<List<TicketsOffers>>> {
        TicketsOffersRepositoryImp(networkSource = get())
    }

    single<TicketsRepository<List<Ticket>>> {
        TicketsRepositoryImpl(networkSource = get())
    }
}

internal val domainModule = module {
    factory<GetOffers> {
        GetOffers(repository = get())
    }
    factory<GetTickets> {
        GetTickets(repository = get())
    }
    factory<GetTicketsOffers> {
        GetTicketsOffers(repository = get())
    }
    factory<ReadCityState> {
        ReadCityState(repository = get())
    }
    factory<SaveCityState>{
        SaveCityState(repository = get())
    }
}

internal val presentationModule = module {
    viewModel<OffersViewModel> {
        OffersViewModel(getOffers = get())
    }
}

val homeModule = module {
    includes(dataModule, domainModule, presentationModule)
}