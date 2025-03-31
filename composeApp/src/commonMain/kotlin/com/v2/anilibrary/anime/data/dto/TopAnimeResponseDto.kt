package com.v2.anilibrary.anime.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopAnimeResponseDto(
    @SerialName("data") val data: List<AnimeDto>,
    @SerialName("pagination") val paging: PaginationDTO
)