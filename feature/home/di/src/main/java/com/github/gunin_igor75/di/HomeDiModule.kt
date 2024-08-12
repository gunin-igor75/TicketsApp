package com.github.gunin_igor75.di

import com.github.gunin_igor75.data.repository.FindCountryRepositoryImpl
import com.github.gunin_igor75.data.repository.OffersRepositoryImpl
import com.github.gunin_igor75.data.repository.TicketsOffersRepositoryImp
import com.github.gunin_igor75.data.repository.TicketsRepositoryImpl
import com.github.gunin_igor75.domain.model.Offer
import com.github.gunin_igor75.domain.model.Ticket
import com.github.gunin_igor75.domain.model.TicketsOffers
import com.github.gunin_igor75.domain.repository.FindCountryRepository
import com.github.gunin_igor75.domain.repository.OffersRepository
import com.github.gunin_igor75.domain.repository.TicketOffersRepository
import com.github.gunin_igor75.domain.repository.TicketsRepository
import com.github.gunin_igor75.domain.usecase.GetCountryItems
import com.github.gunin_igor75.domain.usecase.GetOffers
import com.github.gunin_igor75.domain.usecase.GetTicket
import com.github.gunin_igor75.domain.usecase.GetTickets
import com.github.gunin_igor75.domain.usecase.GetTicketsOffers
import com.github.gunin_igor75.domain.usecase.SaveTicket
import com.github.gunin_igor75.presentation.screens.countryselected.CountrySelectedViewModel
import com.github.gunin_igor75.presentation.screens.destination.FindCountryViewModel
import com.github.gunin_igor75.presentation.screens.offers.OffersTextEditViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val dataModule = module {
    single<OffersRepository<List<Offer>>> {
        OffersRepositoryImpl(
            networkSource = get(),
            ticketDataSource = get()
        )
    }
    single<TicketOffersRepository<List<TicketsOffers>>> {
        TicketsOffersRepositoryImp(networkSource = get())
    }

    single<TicketsRepository<List<Ticket>>> {
        TicketsRepositoryImpl(networkSource = get())
    }
    single<FindCountryRepository> {
        FindCountryRepositoryImpl()
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
    factory<GetTicket> {
        GetTicket(repository = get())
    }
    factory<SaveTicket> {
        SaveTicket(repository = get())
    }
    factory<GetCountryItems> {
        GetCountryItems(repository = get())
    }
}

internal val presentationModule = module {
    viewModel<OffersTextEditViewModel> {
        OffersTextEditViewModel(
            getOffers = get(),
            saveTicket = get(),
            getTicket = get()
        )
    }
    viewModel<FindCountryViewModel> {
        FindCountryViewModel(
            getCountryItems = get(),
            saveTicket = get(),
            getTicket = get()
        )
    }
    viewModel<CountrySelectedViewModel>{
        CountrySelectedViewModel(
            getTicketsOffers = get(),
            saveTicket = get()
        )
    }
}

val homeModule = module {
    includes(dataModule, domainModule, presentationModule)
}