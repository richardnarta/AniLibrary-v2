package com.v2.anilibrary.anime.data.mappers

import com.v2.anilibrary.anime.data.dto.AnimeReviewDto
import com.v2.anilibrary.anime.domain.AnimeReview

fun AnimeReviewDto.toAnimeReview(): AnimeReview {
    return AnimeReview(
        id = id,
        date = dateTime,
        review = review,
        score = score,
        tag = tag,
        spoilerStatus = spoilerStatus,
        userProfile = profileUrl,
        userUsername = profileUsername
    )
}