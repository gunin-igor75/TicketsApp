package com.github.gunin_igor75.bridge_di_module

import com.core.local.LocalSource
import com.core.local.dataprovides.SharedPreferencesSource
import com.core.network.NetworkSource
import com.core.network.dataprovides.FakeNetworkSource
import com.github.gunin_igor75.di.homeModule
import org.koin.dsl.module

internal val coreModule = module {
    single<NetworkSource> {
        FakeNetworkSource(context = get())
    }
    single<LocalSource> {
        SharedPreferencesSource(context = get())
    }
}

val mainModule = module {
    includes(coreModule, homeModule)
}