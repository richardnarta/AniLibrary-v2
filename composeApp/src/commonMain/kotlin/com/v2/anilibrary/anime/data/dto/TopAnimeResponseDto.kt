package com.v2.anilibrary.anime.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TopAnimeResponseDto(
    val data: List<AnimeDto>
)