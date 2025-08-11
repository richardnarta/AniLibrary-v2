package com.v2.anilibrary.anime.data.mappers

import com.v2.anilibrary.anime.data.dto.AnimeDto
import com.v2.anilibrary.anime.domain.Anime
import com.v2.anilibrary.anime.domain.AnimeItemInfo
import com.v2.anilibrary.anime.domain.AnimeRelation
import com.v2.anilibrary.anime.domain.AnimeTheme
import com.v2.anilibrary.anime.domain.AnimeThemeSong

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
        producers = producers,
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
            openings = songTheme?.openings?.map { item ->
                val songs = item.replace("\"", "")

                AnimeThemeSong(
                    song = songs.split(" by ")[0],
                    singer = songs.split(" by ")[1]
                )
            },
            endings = songTheme?.endings?.map { item ->
                val songs = item.replace("\"", "")

                AnimeThemeSong(
                    song = songs.split(" by ")[0],
                    singer = songs.split(" by ")[1]
                )
            }
        ),
        externalLinks = externalLinks?.map { item ->
            AnimeItemInfo(
                name = item.name,
                url = item.url
            )
        },
        streamingPlatform = streamingPlatform?.map { item ->
            AnimeItemInfo(
                name = item.name,
                url = item.url
            )
        },
    )
}