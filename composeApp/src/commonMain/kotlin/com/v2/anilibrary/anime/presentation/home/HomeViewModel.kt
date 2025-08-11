package com.v2.anilibrary.anime.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import app.cash.paging.Pager
import com.v2.anilibrary.anime.domain.Anime
import com.v2.anilibrary.anime.domain.AnimeFilter
import com.v2.anilibrary.anime.domain.AnimeRepository
import com.v2.anilibrary.anime.domain.AnimeType
import com.v2.anilibrary.core.domain.onError
import com.v2.anilibrary.core.domain.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel (
    private val animeRepository: AnimeRepository,
    private val upcomingAnimePager: Pager<Int, Anime>
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())

    init {
        viewModelScope.launch {
            getTopAiringAnime()
            delay(350L)
            getCurrentSeasonAnime()
            delay(350L)
            getTopRatingAnime()
            delay(1000L)
            getUpcomingAnime()
        }
    }

    val state = _state
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }

            is HomeAction.OnTopAiringAnimeSwitched -> {
                _state.update {
                    it.copy(selectedTopAiringAnime = action.index)
                }
            }
        }
    }

    private fun getTopAiringAnime() = viewModelScope.launch {
        _state.update { it.copy(
            topAiringIsLoading = true,
            topAiringIsError = false
        ) }
        animeRepository
            .getTopAnime(
                type = AnimeType.TV,
                filter = AnimeFilter.AIRING
            )
            .onSuccess { topAiringAnime ->
                _state.update { it.copy(
                    topAiringIsLoading = false,
                    topAiringIsError = false,
                    topAiringAnimeResults = topAiringAnime.distinctBy { anime -> anime.id }
                ) }
            }
            .onError {
                _state.update { it.copy(
                    topAiringIsLoading = false,
                    topAiringIsError = true,
                    topAiringAnimeResults = emptyList()
                ) }
            }
    }

    private fun getCurrentSeasonAnime() = viewModelScope.launch {
        _state.update { it.copy(
            seasonAnimeIsLoading = true,
            seasonAnimeIsError = false
        ) }
        animeRepository
            .getCurrentSeasonAnime()
            .onSuccess { seasonAnime ->
                _state.update { it.copy(
                    seasonAnimeIsLoading = false,
                    seasonAnimeIsError = false,
                    seasonAnimeResults = seasonAnime.distinctBy { anime -> anime.id },
                    seasonAnime = seasonAnime[0].airingSeason
                ) }
            }
            .onError {
                _state.update { it.copy(
                    seasonAnimeIsLoading = false,
                    seasonAnimeIsError = true,
                    seasonAnimeResults = emptyList()
                ) }
            }
    }

    private fun getTopRatingAnime() = viewModelScope.launch {
        _state.update { it.copy(
            topRatingIsLoading = true,
            topRatingIsError = false
        ) }
        animeRepository
            .getTopAnime(
                type = AnimeType.EMPTY,
                filter = AnimeFilter.EMPTY
            )
            .onSuccess { topRating ->
                _state.update { it.copy(
                    topRatingIsLoading = false,
                    topRatingIsError = false,
                    topRatingAnimeResults = topRating.distinctBy { anime -> anime.id },
                ) }
            }
            .onError {
                _state.update { it.copy(
                    topRatingIsLoading = false,
                    topRatingIsError = true,
                    topRatingAnimeResults = emptyList()
                ) }
            }
    }

    private fun getUpcomingAnime() = viewModelScope.launch {
        _state.update { it.copy(
            upcomingAnimeResults = upcomingAnimePager.flow.cachedIn(viewModelScope)
        ) }
    }
}