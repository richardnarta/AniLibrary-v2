package com.v2.anilibrary.anime.domain

data class Anime(
    val id: Int,
    val images: String?,
    val title: String,
    val jpTitle: String?,
    val enTitle: String?,
    val type: String?,
    val source: String?,
    val episodeCount: Int?,
    val status: String,
    val airingDate: String?,
    val duration: String?,
    val ageRating: String?,
    val rating: Double?,
    val ratingUserCount: Int?,
    val rank: Int?,
    val synopsis: String,
    val airingSeason: String?,
    val broadcastTime: String?,
    val studios: List<String>?,
    val genres: List<String>?,
    val themes: List<String>?,
    val demographics: List<String>?,
    val animeRelations: List<AnimeRelation>?,
    val songTheme: AnimeTheme?,
    val officialSite: String?,
    val xUsername: String?,
    val xUrl: String?,
    val streamingPlatform: List<AnimeItemInfo>?,
)

data class AnimeRelation(
    val relationType: String,
    val entry: List<AnimeItemInfo>?,
)

data class AnimeTheme(
    val openings: List<String>?,
    val endings: List<String>?,
)

data class AnimeItemInfo(
    val id: Int,
    val type: String,
    val name: String,
    val url: String,
)