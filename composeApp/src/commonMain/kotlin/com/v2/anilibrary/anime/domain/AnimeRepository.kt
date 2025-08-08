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
}