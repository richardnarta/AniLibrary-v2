package com.v2.anilibrary.anime.data.network

import com.v2.anilibrary.anime.data.dto.TopAiringResponseDto
import com.v2.anilibrary.core.domain.DataError
import com.v2.anilibrary.core.domain.Result

interface RemoteAnimeDataSource {
    suspend fun getTopAiringAnime(): Result<TopAiringResponseDto, DataError.Remote>
}