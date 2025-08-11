package com.v2.anilibrary.anime.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeCharacterDto(
    @SerialName("character") val character: CharacterDto,
    @SerialName("role") val role: String,
) {
    val id: Int
        get() = character.id

    val imageUrl: String
        get() = character.images.jpg.normal

    val characterName: String
        get() = character.name
}

@Serializable
data class CharacterImageType(
    val jpg: CharacterImageQuality,
    val webp: CharacterImageQuality,
)

@Serializable
data class CharacterImageQuality(
    @SerialName("image_url") val normal: String,
)

@Serializable
data class CharacterDto(
    @SerialName("mal_id") val id: Int,
    @SerialName("images") val images: CharacterImageType,
    @SerialName("name") val name: String,
)
