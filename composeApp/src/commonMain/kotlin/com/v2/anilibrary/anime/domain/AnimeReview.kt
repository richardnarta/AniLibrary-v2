package com.v2.anilibrary.anime.domain

data class AnimeReview(
    val id: Int,
    val date: String,
    val review: String,
    val score: Int,
    val tag: String,
    val spoilerStatus: Boolean,
    val userProfile: String,
    val userUsername: String
)