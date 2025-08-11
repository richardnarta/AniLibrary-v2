package com.v2.anilibrary.anime.data.mappers

import com.v2.anilibrary.anime.data.dto.AnimeCoverType

fun AnimeCoverType.toAnimePictureUrl(): String {
    return jpg.high
}
