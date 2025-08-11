package com.v2.anilibrary.anime.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AnimeVideoResponseDto(
    val data: AnimeVideoDataDto
)

@Serializable
data class AnimeVideoDataDto(
    val promo: List<AnimeTrailerDto>,
)
