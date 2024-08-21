package com.github.gunin_igor75.di

import com.core.network.NetworkSource
import com.core.network.dataprovides.FakeNetworkSource
import org.koin.dsl.module


internal val netWorkModule = module {
    single<NetworkSource> {
        FakeNetworkSource(context = get())
    }
}