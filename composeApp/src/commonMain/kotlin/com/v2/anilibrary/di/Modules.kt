package com.v2.anilibrary.di

import androidx.paging.PagingConfig
import app.cash.paging.Pager
import com.v2.anilibrary.anime.data.network.KtorRemoteAnimeDataSource
import com.v2.anilibrary.anime.data.network.RemoteAnimeDataSource
import com.v2.anilibrary.anime.data.paging.UpcomingAnimePagingSource
import com.v2.anilibrary.anime.data.repository.AnimeRepository
import com.v2.anilibrary.anime.presentation.home.HomeViewModel
import com.v2.anilibrary.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteAnimeDataSource).bind<RemoteAnimeDataSource>()
    singleOf(::AnimeRepository).bind<com.v2.anilibrary.anime.domain.AnimeRepository>()

    factory(named("UpcomingPager")) {
        Pager(
            config = PagingConfig(pageSize = 25, initialLoadSize = 25),
            pagingSourceFactory = { UpcomingAnimePagingSource(get()) }
        )
    }

    viewModel {
        HomeViewModel(
            get(),
            get(named("UpcomingPager"))
        )
    }
}