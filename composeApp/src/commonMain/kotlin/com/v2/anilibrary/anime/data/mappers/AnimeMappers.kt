package com.v2.anilibrary.anime.data.mappers

import com.v2.anilibrary.anime.data.dto.AnimeDto
import com.v2.anilibrary.anime.domain.Anime
import com.v2.anilibrary.anime.domain.AnimeItemInfo
import com.v2.anilibrary.anime.domain.AnimeRelation
import com.v2.anilibrary.anime.domain.AnimeTheme

fun AnimeDto.toAnime(): Anime {
    return Anime(
        id = id,
        images = imageUrl,
        title = title,
        jpTitle = jpTitle,
        enTitle = enTitle,
        type = type,
        source = source,
        episodeCount = episodeCount,
        status = status,
        airingDate = airingDate,
        duration = duration,
        ageRating = ageRating,
        rating = rating,
        ratingUserCount = ratingUserCount,
        rank = rank,
        synopsis = synopsis,
        airingSeason = airingSeason,
        broadcastTime = broadcastTime,
        studios = studios,
        genres = genres,
        themes = themes,
        demographics = demographics,
        animeRelations = animeRelations?.map { item ->
            AnimeRelation(
                relationType = item.relationType,
                entry = item.entry?.map { anime ->
                    AnimeItemInfo(
                        id = anime.id,
                        type = anime.type,
                        name = anime.name,
                        url = anime.url
                    )
                }
            )
        },
        songTheme = AnimeTheme(
            openings = songTheme?.openings,
            endings = songTheme?.endings
        ),
        officialSite = officialSite,
        xUsername = xUsername,
        xUrl = xUrl,
        streamingPlatform = streamingPlatform?.map { item ->
            AnimeItemInfo(
                id = item.id,
                type = item.type,
                name = item.name,
                url = item.url
            )
        }
    )
}