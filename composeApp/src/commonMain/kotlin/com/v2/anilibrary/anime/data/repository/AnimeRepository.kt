package com.v2.anilibrary.anime.data.repository

import com.v2.anilibrary.anime.data.mappers.toAnime
import com.v2.anilibrary.anime.data.network.RemoteAnimeDataSource
import com.v2.anilibrary.anime.domain.Anime
import com.v2.anilibrary.core.domain.DataError
import com.v2.anilibrary.core.domain.Result
import com.v2.anilibrary.core.domain.map

class AnimeRepository(
    private val remoteAnimeDataSource: RemoteAnimeDataSource
): com.v2.anilibrary.anime.domain.AnimeRepository {
    override suspend fun getTopAiringAnime(): Result<List<Anime>, DataError.Remote> {
        return remoteAnimeDataSource.getTopAiringAnime().map { dto ->
            dto.data.map { it.toAnime() }
        }
    }
}