package com.v2.anilibrary.anime.data.mappers

import com.v2.anilibrary.anime.data.dto.AnimeTrailerDto
import com.v2.anilibrary.anime.domain.AnimeTrailer

fun AnimeTrailerDto.toAnimeTrailer(): AnimeTrailer {
    return AnimeTrailer(
        title = title,
        url = trailer.url,
        imageCover = trailer.images.normal
    )
}