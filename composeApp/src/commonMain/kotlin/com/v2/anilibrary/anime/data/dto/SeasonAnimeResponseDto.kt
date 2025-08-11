package com.v2.anilibrary.anime.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SeasonAnimeResponseDto(
    val data: List<AnimeDto>
)
