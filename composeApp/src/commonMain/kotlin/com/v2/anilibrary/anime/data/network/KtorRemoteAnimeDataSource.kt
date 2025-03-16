package com.v2.anilibrary.anime.data.network

import com.v2.anilibrary.anime.data.dto.TopAiringResponseDto
import com.v2.anilibrary.core.data.safeCall
import com.v2.anilibrary.core.domain.DataError
import com.v2.anilibrary.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KtorRemoteAnimeDataSource(
    private val httpClient: HttpClient
): RemoteAnimeDataSource {

    override suspend fun getTopAiringAnime(): Result<TopAiringResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "https://api.jikan.moe/v4/top/anime"
            ) {
                parameter("filter", "airing")
                parameter("type", "tv")
                parameter("limit", "10")
            }
        }
    }
}