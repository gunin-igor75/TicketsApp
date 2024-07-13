package com.github.gunin_igor75.ticketsapp.app

import android.app.Application
import com.github.gunin_igor75.bridge_di_module.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TicketsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TicketsApp)
            modules(
                listOf(
                    coreModule
                )
            )
        }
    }
}