package com.v2.anilibrary.di

import com.v2.anilibrary.anime.data.network.KtorRemoteAnimeDataSource
import com.v2.anilibrary.anime.data.network.RemoteAnimeDataSource
import com.v2.anilibrary.anime.presentation.home.HomeViewModel
import com.v2.anilibrary.anime.data.repository.AnimeRepository
import com.v2.anilibrary.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteAnimeDataSource).bind<RemoteAnimeDataSource>()
    singleOf(::AnimeRepository).bind<com.v2.anilibrary.anime.domain.AnimeRepository>()

    viewModelOf(::HomeViewModel)
}