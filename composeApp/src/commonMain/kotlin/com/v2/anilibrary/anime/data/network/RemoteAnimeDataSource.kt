package com.v2.anilibrary.anime.data.network

import com.v2.anilibrary.anime.data.dto.AnimeCharacterResponseDto
import com.v2.anilibrary.anime.data.dto.AnimePictureResponseDto
import com.v2.anilibrary.anime.data.dto.AnimeResponseDto
import com.v2.anilibrary.anime.data.dto.AnimeReviewResponseDto
import com.v2.anilibrary.anime.data.dto.AnimeVideoResponseDto
import com.v2.anilibrary.anime.data.dto.SeasonAnimeResponseDto
import com.v2.anilibrary.anime.data.dto.TopAnimeResponseDto
import com.v2.anilibrary.anime.domain.AnimeFilter
import com.v2.anilibrary.anime.domain.AnimeType
import com.v2.anilibrary.core.domain.DataError
import com.v2.anilibrary.core.domain.Result

interface RemoteAnimeDataSource {
    suspend fun getTopAnime(
        type: AnimeType,
        filter: AnimeFilter,
        page: Int,
        limit: Int
    ): Result<TopAnimeResponseDto, DataError.Remote>

    suspend fun getCurrentSeasonAnime(
        type: AnimeType,
        page: Int,
        limit: Int
    ): Result<SeasonAnimeResponseDto, DataError.Remote>

    suspend fun getFullAnimeDetail(
        animeId: Int,
    ): Result<AnimeResponseDto, DataError.Remote>

    suspend fun getAnimePicture(
        animeId: Int,
    ): Result<AnimePictureResponseDto, DataError.Remote>

    suspend fun getAnimeCharacter(
        animeId: Int,
    ): Result<AnimeCharacterResponseDto, DataError.Remote>

    suspend fun getAnimePromotionalVideo(
        animeId: Int,
    ): Result<AnimeVideoResponseDto, DataError.Remote>

    suspend fun getAnimeReview(
        animeId: Int,
        page: Int,
        limit: Int
    ): Result<AnimeReviewResponseDto, DataError.Remote>
}