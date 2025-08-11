package com.v2.anilibrary.anime.data.repository

import com.v2.anilibrary.anime.data.mappers.toAnime
import com.v2.anilibrary.anime.data.mappers.toAnimeCharacter
import com.v2.anilibrary.anime.data.mappers.toAnimePictureUrl
import com.v2.anilibrary.anime.data.mappers.toAnimeReview
import com.v2.anilibrary.anime.data.mappers.toAnimeTrailer
import com.v2.anilibrary.anime.data.network.RemoteAnimeDataSource
import com.v2.anilibrary.anime.domain.Anime
import com.v2.anilibrary.anime.domain.AnimeCharacter
import com.v2.anilibrary.anime.domain.AnimeFilter
import com.v2.anilibrary.anime.domain.AnimeReview
import com.v2.anilibrary.anime.domain.AnimeTrailer
import com.v2.anilibrary.anime.domain.AnimeType
import com.v2.anilibrary.core.domain.DataError
import com.v2.anilibrary.core.domain.Result
import com.v2.anilibrary.core.domain.map

class AnimeRepository(
    private val remoteAnimeDataSource: RemoteAnimeDataSource
): com.v2.anilibrary.anime.domain.AnimeRepository {
    override suspend fun getTopAnime(
        type: AnimeType,
        filter: AnimeFilter,
        page: Int,
        limit: Int
    ): Result<List<Anime>, DataError.Remote> {
        return remoteAnimeDataSource.getTopAnime(
            type, filter, page, limit
        ).map { dto ->
            dto.data.map { it.toAnime() }
        }
    }

    override suspend fun getCurrentSeasonAnime(
        type: AnimeType,
        page: Int,
        limit: Int
    ): Result<List<Anime>, DataError.Remote> {
        return remoteAnimeDataSource.getCurrentSeasonAnime(
            type, page, limit
        ).map { dto ->
            dto.data.map { it.toAnime() }
        }
    }

    override suspend fun getFullAnimeDetail(
        animeId: Int
    ): Result<Anime, DataError.Remote> {
        return remoteAnimeDataSource.getFullAnimeDetail(
            animeId
        ).map { dto ->
            dto.data.toAnime()
        }
    }

    override suspend fun getAnimePicture(
        animeId: Int
    ): Result<List<String>, DataError.Remote> {
        return remoteAnimeDataSource.getAnimePicture(
            animeId
        ).map { dto ->
            dto.data.map { it.toAnimePictureUrl() }
        }
    }

    override suspend fun getAnimeCharacter(
        animeId: Int
    ): Result<List<AnimeCharacter>, DataError.Remote> {
        return remoteAnimeDataSource.getAnimeCharacter(
            animeId
        ).map { dto ->
            dto.data.map { it.toAnimeCharacter() }
        }
    }

    override suspend fun getAnimePromotionalVideo(
        animeId: Int
    ): Result<List<AnimeTrailer>, DataError.Remote> {
        return remoteAnimeDataSource.getAnimePromotionalVideo(
            animeId
        ).map { dto ->
            dto.data.promo.map { it.toAnimeTrailer() }
        }
    }

    override suspend fun getAnimeReview(
        animeId: Int,
        page: Int,
        limit: Int
    ): Result<List<AnimeReview>, DataError.Remote> {
        return remoteAnimeDataSource.getAnimeReview(
            animeId, page, limit
        ).map { dto ->
            dto.data.map { it.toAnimeReview() }
        }
    }
}