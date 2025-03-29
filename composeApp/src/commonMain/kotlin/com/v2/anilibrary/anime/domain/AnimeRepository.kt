package com.v2.anilibrary.anime.domain

import com.v2.anilibrary.core.domain.DataError
import com.v2.anilibrary.core.domain.Result

interface AnimeRepository {
    suspend fun getTopAiringAnime(): Result<List<Anime>, DataError.Remote>

    suspend fun getCurrentSeasonAnime(
        filter: String = "tv",
        page: Int = 1,
        limit: Int = 10
    ): Result<List<Anime>, DataError.Remote>
}