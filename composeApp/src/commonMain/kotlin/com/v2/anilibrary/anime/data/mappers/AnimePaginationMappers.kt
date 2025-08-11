package com.v2.anilibrary.anime.data.mappers

import com.v2.anilibrary.anime.data.dto.TopAnimeResponseDto
import com.v2.anilibrary.anime.domain.Anime
import com.v2.anilibrary.core.domain.PaginationItems

fun TopAnimeResponseDto.toPaging(): PaginationItems<Anime> {
    return PaginationItems(
        data = this.data.map { dto ->
            dto.toAnime()
        },
        hasNextPage = this.paging.nextPage,
        totalCount = this.paging.items?.totalPages ?: 0
    )
}