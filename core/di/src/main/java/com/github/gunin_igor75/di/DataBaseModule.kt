package com.github.gunin_igor75.di

import android.app.Application
import androidx.room.Room
import com.core.common.utils.Constants.Companion.DB_NAME
import com.core.local.LocalSourceProvider
import com.core.local.db.TicketDao
import com.core.local.db.TicketDataBase
import com.core.local.preferencesprovides.SharedPreferencesSourceProvider
import org.koin.dsl.module


internal fun providesDataBase(application: Application): TicketDataBase {
    return Room.databaseBuilder(
        context = application,
        klass = TicketDataBase::class.java,
        name = DB_NAME
    ).build()
}

internal fun providesTicketDao(ticketDataBase: TicketDataBase): TicketDao = ticketDataBase.ticketDao()

internal val dataBaseModule = module {
    single<TicketDataBase> {
        providesDataBase(application = get())
    }
    single<TicketDao> {
        providesTicketDao(ticketDataBase = get())
    }
    single<LocalSourceProvider> {
        SharedPreferencesSourceProvider(context = get())
    }
}

