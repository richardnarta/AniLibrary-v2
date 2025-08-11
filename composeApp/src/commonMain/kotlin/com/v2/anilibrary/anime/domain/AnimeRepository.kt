package com.v2.anilibrary.anime.domain

import com.v2.anilibrary.core.domain.DataError
import com.v2.anilibrary.core.domain.Result

interface AnimeRepository {
    suspend fun getTopAnime(
        type: AnimeType,
        filter: AnimeFilter,
        page: Int = 1,
        limit: Int = 10
    ): Result<List<Anime>, DataError.Remote>

    suspend fun getCurrentSeasonAnime(
        type: AnimeType = AnimeType.TV,
        page: Int = 1,
        limit: Int = 10
    ): Result<List<Anime>, DataError.Remote>

    suspend fun getFullAnimeDetail(
        animeId: Int
    ): Result<Anime, DataError.Remote>

    suspend fun getAnimePicture(
        animeId: Int
    ): Result<List<String>, DataError.Remote>

    suspend fun getAnimeCharacter(
        animeId: Int
    ): Result<List<AnimeCharacter>, DataError.Remote>

    suspend fun getAnimePromotionalVideo(
        animeId: Int
    ): Result<List<AnimeTrailer>, DataError.Remote>

    suspend fun getAnimeReview(
        animeId: Int,
        page: Int = 1,
        limit: Int = 10
    ): Result<List<AnimeReview>, DataError.Remote>
}