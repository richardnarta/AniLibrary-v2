package com.v2.anilibrary.anime.domain

enum class AnimeType {
    TV, MOVIE, OVA, SPECIAL, ONA, MUSIC, EMPTY,
}

enum class AnimeFilter {
    AIRING, UPCOMING, POPULARITY, FAVORITE, EMPTY,
}

fun AnimeType.value(): String {
    return when (this) {
        AnimeType.TV -> "tv"
        AnimeType.MOVIE -> "movie"
        AnimeType.OVA -> "ova"
        AnimeType.SPECIAL -> "special"
        AnimeType.ONA -> "ona"
        AnimeType.MUSIC -> "music"
        AnimeType.EMPTY -> ""
    }
}

fun AnimeFilter.value(): String {
    return when (this) {
        AnimeFilter.AIRING -> "airing"
        AnimeFilter.UPCOMING -> "upcoming"
        AnimeFilter.POPULARITY -> "bypopularity"
        AnimeFilter.FAVORITE -> "favorite"
        AnimeFilter.EMPTY -> ""
    }
}