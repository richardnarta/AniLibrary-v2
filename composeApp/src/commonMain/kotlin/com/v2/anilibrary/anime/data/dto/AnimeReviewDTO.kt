package com.v2.anilibrary.anime.data.dto

import com.v2.anilibrary.core.utils.convertUTCToLocal
import com.v2.anilibrary.core.utils.formatFullDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeReviewDTO(
    @SerialName("mal_id") val id: Int,
    @SerialName("date") val date: String,
    @SerialName("review") val review: String,
    @SerialName("score") val score: Int,
    @SerialName("tags") val tags: List<String>,
    @SerialName("is_spoiler") val spoilerStatus: Boolean,
    @SerialName("user") val user: UserReview
) {
    val profileUrl: String
        get() = user.images.jpg.normal

    val profileUsername: String
        get() = user.username

    val dateTime: String
        get() = date
            .convertUTCToLocal()
            .formatFullDateTime()

    val tag: String
        get() = if (
            tags.contains("Recommended")
        ) {
            "Recommended"
        } else if (
            tags.contains("Not Recommended")
        ) {
            "Not Recommended"
        } else {
            "Mixed Feelings"
        }
}

@Serializable
data class ProfileCoverType(
    val jpg: ProfileCoverQuality,
    val webp: ProfileCoverQuality,
)

@Serializable
data class ProfileCoverQuality(
    @SerialName("image_url") val normal: String,
)

@Serializable
data class UserReview(
    @SerialName("username") val username: String,
    @SerialName("images") val images: ProfileCoverType,
)
