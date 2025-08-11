package com.v2.anilibrary.anime.data.dto

import com.v2.anilibrary.core.data.PaginationDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeReviewResponseDto(
    @SerialName("pagination") val paging: PaginationDto,
    val data: List<AnimeReviewDto>
)
