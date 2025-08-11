package com.v2.anilibrary.anime.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeCharacterResponseDto (
    @SerialName("data") val data: List<AnimeCharacterDto>,
)