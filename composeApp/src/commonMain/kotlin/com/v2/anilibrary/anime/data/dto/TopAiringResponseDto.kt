package com.v2.anilibrary.anime.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TopAiringResponseDto(
    val data: List<AnimeDto>
)