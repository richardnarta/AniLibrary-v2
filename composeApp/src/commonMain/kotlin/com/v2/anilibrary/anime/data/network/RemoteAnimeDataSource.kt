package com.v2.anilibrary.anime.data.network

import com.v2.anilibrary.anime.data.dto.SeasonAnimeResponseDTO
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
    ): Result<SeasonAnimeResponseDTO, DataError.Remote>
}