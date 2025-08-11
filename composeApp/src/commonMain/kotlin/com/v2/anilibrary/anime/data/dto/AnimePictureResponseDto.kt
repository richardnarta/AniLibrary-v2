package com.v2.anilibrary.anime.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AnimePictureResponseDto(
    val data: List<AnimeCoverType>
)
