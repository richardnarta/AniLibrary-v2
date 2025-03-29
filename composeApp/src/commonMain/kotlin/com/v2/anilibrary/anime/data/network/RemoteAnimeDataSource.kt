package com.v2.anilibrary.anime.data.network

import com.v2.anilibrary.anime.data.dto.SeasonAnimeResponseDTO
import com.v2.anilibrary.anime.data.dto.TopAiringResponseDto
import com.v2.anilibrary.core.domain.DataError
import com.v2.anilibrary.core.domain.Result

interface RemoteAnimeDataSource {
    suspend fun getTopAiringAnime(): Result<TopAiringResponseDto, DataError.Remote>

    suspend fun getCurrentSeasonAnime(
        filter: String = "tv",
        page: Int = 0,
        limit: Int = 10
    ): Result<SeasonAnimeResponseDTO, DataError.Remote>
}