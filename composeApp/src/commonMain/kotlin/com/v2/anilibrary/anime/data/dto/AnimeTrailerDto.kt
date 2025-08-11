package com.v2.anilibrary.anime.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeTrailerDto(
    @SerialName("title") val title: String,
    @SerialName("trailer") val trailer: TrailerDto,
)

@Serializable
data class TrailerImageQuality(
    @SerialName("large_image_url") val normal: String,
)

@Serializable
data class TrailerDto(
    @SerialName("url") val url: String,
    @SerialName("embed_url") val embedUrl: String,
    @SerialName("images") val images:TrailerImageQuality,
)
