package com.v2.anilibrary.core.utils

import androidx.compose.runtime.Composable
import com.v2.anilibrary.R
import com.v2.anilibrary.anime.domain.Anime
import androidx.compose.ui.res.stringResource
import com.v2.anilibrary.anime.domain.AnimeItemInfo
import com.v2.anilibrary.anime.domain.AnimeRelation
import com.v2.anilibrary.anime.domain.AnimeTheme
import com.v2.anilibrary.anime.domain.AnimeThemeSong

object DummyAnime {
    @Composable
    fun getAnime(): Anime = Anime(
        id = 0,
        images = stringResource(R.string.dummy_anime_images),
        title = stringResource(R.string.dummy_anime_title),
        jpTitle = stringResource(R.string.dummy_anime_jp_title),
        enTitle = stringResource(R.string.dummy_anime_en_title),
        type = stringResource(R.string.dummy_anime_type),
        source = stringResource(R.string.dummy_anime_source),
        episodeCount = stringResource(R.string.dummy_anime_episodes).toInt(),
        status = stringResource(R.string.dummy_anime_status),
        airingDate = stringResource(R.string.dummy_anime_airing_date),
        duration = stringResource(R.string.dummy_anime_duration),
        ageRating = stringResource(R.string.dummy_anime_age_rating),
        rating = stringResource(R.string.dummy_anime_rating).toDouble(),
        ratingUserCount = stringResource(R.string.dummy_anime_rating_user).toInt(),
        rank = stringResource(R.string.dummy_anime_rank).toInt(),
        synopsis = stringResource(R.string.dummy_anime_synopsis),
        airingSeason = stringResource(R.string.dummy_anime_season),
        broadcastTime = stringResource(R.string.dummy_anime_broadcast),
        studios = listOf(
            stringResource(R.string.dummy_anime_studio_1),
        ),
        genres = listOf(
            stringResource(R.string.dummy_anime_genre_1),
            stringResource(R.string.dummy_anime_genre_2),
        ),
        themes = listOf(
            stringResource(R.string.dummy_anime_theme_1),
            stringResource(R.string.dummy_anime_theme_2),
        ),
        demographics = listOf(
            stringResource(R.string.dummy_anime_demographic),
        ),
        animeRelations = listOf(
            AnimeRelation(
                relationType = stringResource(R.string.prequel),
                entry = listOf(
                    AnimeItemInfo(
                        id = 0,
                        type = "x",
                        name = stringResource(R.string.dummy_anime_prequel),
                        url = "x"
                    )
                )
            ),
            AnimeRelation(
                relationType = stringResource(R.string.sequel),
                entry = listOf(
                    AnimeItemInfo(
                        id = 0,
                        type = "x",
                        name = stringResource(R.string.dummy_anime_sequel),
                        url = "x"
                    )
                )
            ),
            AnimeRelation(
                relationType = stringResource(R.string.adaptation),
                entry = listOf(
                    AnimeItemInfo(
                        id = 0,
                        type = "x",
                        name = stringResource(R.string.dummy_anime_adaptation),
                        url = "x"
                    )
                )
            )
        ),
        songTheme = AnimeTheme(
            openings = listOf(
                AnimeThemeSong(
                    song = stringResource(R.string.dummy_anime_opening_song),
                    singer = stringResource(R.string.dummy_anime_singer)
                ),
            ),
            endings = listOf(
                AnimeThemeSong(
                    song = stringResource(R.string.dummy_anime_ending_1_song),
                    singer = stringResource(R.string.dummy_anime_singer)
                ),
                AnimeThemeSong(
                    song = stringResource(R.string.dummy_anime_ending_2_song),
                    singer = stringResource(R.string.dummy_anime_singer)
                )
            )
        ),
        officialSite = stringResource(R.string.dummy_anime_official_site),
        xUsername = stringResource(R.string.dummy_anime_x),
        xUrl = stringResource(R.string.dummy_anime_x_url),
        streamingPlatform = listOf(
            AnimeItemInfo(
                id = 0,
                type = "x",
                name = stringResource(R.string.dummy_anime_streaming_platform_1),
                url = "x"
            ),
            AnimeItemInfo(
                id = 0,
                type = "x",
                name = stringResource(R.string.dummy_anime_streaming_platform_2),
                url = "x"
            )
        ),
    )
}