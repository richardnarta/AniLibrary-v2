package com.v2.anilibrary.anime.presentation.home

import app.cash.paging.PagingData
import com.v2.anilibrary.anime.domain.Anime
import com.v2.anilibrary.core.presentation.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class HomeState(
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null,

    val topAiringAnimeResults: List<Anime> = emptyList(),
    val topAiringIsLoading: Boolean = true,
    val topAiringIsError: Boolean = false,
    val selectedTopAiringAnime: Int = 0,

    val seasonAnime: String? = null,
    val seasonAnimeResults: List<Anime> = emptyList(),
    val seasonAnimeIsLoading: Boolean = true,
    val seasonAnimeIsError: Boolean = false,

    val topRatingAnimeResults: List<Anime> = emptyList(),
    val topRatingIsLoading: Boolean = true,
    val topRatingIsError: Boolean = false,

    val upcomingAnimeResults: Flow<PagingData<Anime>> = flowOf(PagingData.empty()),
)