package com.v2.anilibrary.anime.presentation.detail

import com.v2.anilibrary.anime.domain.Anime
import com.v2.anilibrary.anime.domain.AnimeCharacter
import com.v2.anilibrary.anime.domain.AnimeReview
import com.v2.anilibrary.anime.domain.AnimeTrailer
import com.v2.anilibrary.core.presentation.UiText

data class DetailState (
    val errorMessage: UiText? = null,

    val scrollPosition: Int = 0,
    val synopsisIsExpanded: Boolean = false,
    val synopsisIsOverflowed: Boolean = false,

    val detailAnimeResult: Anime? = null,
    val detailAnimeIsLoading: Boolean = true,
    val detailAnimeIsError: Boolean = false,

    val animePictureResult: String? = null,
    val animePictureIsLoading: Boolean = true,
    val animePictureIsError: Boolean = false,

    val animeCharacterResult: List<AnimeCharacter>? = null,
    val animeCharacterIsLoading: Boolean = true,
    val animeCharacterIsError: Boolean = false,

    val animeTrailerVideoResult: List<AnimeTrailer>? = null,
    val animeTrailerVideoIsLoading: Boolean = true,
    val animeTrailerVideoIsError: Boolean = false,

    val animeReviewResult: List<AnimeReview>? = null,
    val animeReviewIsLoading: Boolean = true,
    val animeReviewIsError: Boolean = false,
)