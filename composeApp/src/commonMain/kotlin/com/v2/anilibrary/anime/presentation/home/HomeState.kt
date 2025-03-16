package com.v2.anilibrary.anime.presentation.home

import com.v2.anilibrary.anime.domain.Anime
import com.v2.anilibrary.core.presentation.UiText

data class HomeState(
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null,

    val topAiringAnimeResults: List<Anime> = emptyList(),
    val topAiringIsLoading: Boolean = true,
    val topAiringIsError: Boolean = false,
    val selectedTopAiringAnime: Int = 0,
)