package com.github.gunin_igor75.di

import com.core.local.datasource.TicketLocalDataSource
import org.koin.dsl.module

internal val dataSourceModule = module {
    factory<TicketLocalDataSource> {
        TicketLocalDataSource(ticketApi = get())
    }
}