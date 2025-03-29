package com.v2.anilibrary.anime.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.v2.anilibrary.anime.domain.AnimeRepository
import com.v2.anilibrary.core.domain.onError
import com.v2.anilibrary.core.domain.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel (
    private val animeRepository: AnimeRepository
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state
        .onStart {
            getTopAiringAnime()
            getCurrentSeasonAnime()
        }
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
            .getTopAiringAnime()
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
}