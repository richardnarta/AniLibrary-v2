package com.v2.anilibrary.anime.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.v2.anilibrary.anime.domain.AnimeRepository
import com.v2.anilibrary.core.domain.onError
import com.v2.anilibrary.core.domain.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val animeRepository: AnimeRepository,
): ViewModel() {
    private val _state = MutableStateFlow(DetailState())
    val state = _state
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: DetailAction) {
        when (action) {
            is DetailAction.LastScrollPosition -> {
                _state.update {
                    it.copy(scrollPosition = action.y)
                }
            }

            is DetailAction.ViewMoreSynopsis -> {
                _state.update {
                    it.copy(
                        synopsisIsExpanded = !action.isExpanded
                    )
                }
            }

            is DetailAction.OverflowSynopsis -> {
                _state.update {
                    it.copy(
                        synopsisIsOverflowed = action.isOverFlowed
                    )
                }
            }
        }
    }

    fun getAnimeDetail(
        animeId: Int
    ) = viewModelScope.launch {
        getFullAnimeDetail(animeId)
        delay(350L)
        getAnimePicture(animeId)
        delay(350L)
        getAnimeCharacter(animeId)
        delay(1000L)
        getAnimePromotionalVideo(animeId)
        delay(350L)
        getAnimeReview(animeId)
    }

    private fun getFullAnimeDetail(
        animeId: Int
    ) = viewModelScope.launch {
        _state.update { it.copy(
            detailAnimeIsLoading = true,
            detailAnimeIsError = false
        ) }
        animeRepository
            .getFullAnimeDetail(
                animeId = animeId
            )
            .onSuccess { animeDetail ->
                _state.update { it.copy(
                    detailAnimeIsLoading = false,
                    detailAnimeIsError = false,
                    detailAnimeResult = animeDetail
                ) }
            }
            .onError {
                _state.update { it.copy(
                    detailAnimeIsLoading = false,
                    detailAnimeIsError = true,
                    detailAnimeResult = null
                ) }
            }
    }

    private fun getAnimePicture(
        animeId: Int
    ) = viewModelScope.launch {
        _state.update { it.copy(
            animePictureIsLoading = true,
            animePictureIsError = false
        ) }
        animeRepository
            .getAnimePicture(
                animeId = animeId
            )
            .onSuccess { pictureList ->
                _state.update { it.copy(
                    animePictureIsLoading = false,
                    animePictureIsError = false,
                    animePictureResult = if (pictureList.isNotEmpty()) {
                        pictureList.random()
                    } else {
                        ""
                    }
                ) }
            }
            .onError {
                _state.update { it.copy(
                    animePictureIsLoading = false,
                    animePictureIsError = true,
                    animePictureResult = null
                ) }
            }
    }

    private fun getAnimeCharacter(
        animeId: Int
    ) = viewModelScope.launch {
        _state.update { it.copy(
            animeCharacterIsLoading = true,
            animeCharacterIsError = false
        ) }
        animeRepository
            .getAnimeCharacter(
                animeId = animeId
            )
            .onSuccess { characterList ->
                _state.update { it.copy(
                    animeCharacterIsLoading = false,
                    animeCharacterIsError = false,
                    animeCharacterResult = characterList
                ) }
            }
            .onError {
                _state.update { it.copy(
                    animeCharacterIsLoading = false,
                    animeCharacterIsError = true,
                    animeCharacterResult = null
                ) }
            }
    }

    private fun getAnimePromotionalVideo(
        animeId: Int
    ) = viewModelScope.launch {
        _state.update { it.copy(
            animeTrailerVideoIsLoading = true,
            animeTrailerVideoIsError = false
        ) }
        animeRepository
            .getAnimePromotionalVideo(
                animeId = animeId
            )
            .onSuccess { videoList ->
                _state.update { it.copy(
                    animeTrailerVideoIsLoading = false,
                    animeTrailerVideoIsError = false,
                    animeTrailerVideoResult = videoList
                ) }
            }
            .onError {
                _state.update { it.copy(
                    animeTrailerVideoIsLoading = false,
                    animeTrailerVideoIsError = true,
                    animeTrailerVideoResult = null
                ) }
            }
    }

    private fun getAnimeReview(
        animeId: Int
    ) = viewModelScope.launch {
        _state.update { it.copy(
            animeReviewIsLoading = true,
            animeReviewIsError = false
        ) }
        animeRepository
            .getAnimeReview(
                animeId = animeId
            )
            .onSuccess { reviewList ->
                _state.update { it.copy(
                    animeReviewIsLoading = false,
                    animeReviewIsError = false,
                    animeReviewResult = reviewList
                ) }
            }
            .onError {
                _state.update { it.copy(
                    animeReviewIsLoading = false,
                    animeReviewIsError = true,
                    animeReviewResult = null
                ) }
            }
    }
}