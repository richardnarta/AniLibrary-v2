package com.v2.anilibrary.anime.data.mappers

import com.v2.anilibrary.anime.data.dto.AnimeCharacterDto
import com.v2.anilibrary.anime.domain.AnimeCharacter

fun AnimeCharacterDto.toAnimeCharacter(): AnimeCharacter {
    return AnimeCharacter(
        id = id,
        images = imageUrl,
        name = characterName,
        role = role
    )
}