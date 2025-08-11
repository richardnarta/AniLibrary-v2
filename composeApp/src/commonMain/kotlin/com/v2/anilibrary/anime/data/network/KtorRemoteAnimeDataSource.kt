package com.v2.anilibrary.anime.data.network

import com.v2.anilibrary.anime.data.dto.SeasonAnimeResponseDto
import com.v2.anilibrary.anime.data.dto.TopAnimeResponseDto
import com.v2.anilibrary.anime.domain.AnimeFilter
import com.v2.anilibrary.anime.domain.AnimeType
import com.v2.anilibrary.anime.domain.value
import com.v2.anilibrary.core.data.safeCall
import com.v2.anilibrary.core.domain.DataError
import com.v2.anilibrary.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KtorRemoteAnimeDataSource(
    private val httpClient: HttpClient
): RemoteAnimeDataSource {

    override suspend fun getTopAnime(
        type: AnimeType,
        filter: AnimeFilter,
        page: Int,
        limit: Int
    ): Result<TopAnimeResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "https://api.jikan.moe/v4/top/anime"
            ) {
                parameter("filter", filter.value())
                parameter("type", type.value())
                parameter("page", page)
                parameter("limit", limit)
            }
        }
    }

    override suspend fun getCurrentSeasonAnime(
        type: AnimeType,
        page: Int,
        limit: Int
    ): Result<SeasonAnimeResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "https://api.jikan.moe/v4/seasons/now"
            ) {
                parameter("filter", type.value())
                parameter("limit", limit)
                parameter("page", page)
            }
        }
    }
}