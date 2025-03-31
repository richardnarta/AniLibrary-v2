package com.v2.anilibrary.anime.data.paging

import com.v2.anilibrary.anime.data.mappers.toPaging
import com.v2.anilibrary.anime.data.network.RemoteAnimeDataSource
import com.v2.anilibrary.anime.domain.Anime
import com.v2.anilibrary.anime.domain.AnimeFilter
import com.v2.anilibrary.anime.domain.AnimeType
import com.v2.anilibrary.core.data.JikanPagingDataSource
import com.v2.anilibrary.core.domain.PaginationItems
import com.v2.anilibrary.core.domain.onSuccess

class UpcomingAnimePagingSource(
    private val remoteAnimeDataSource: RemoteAnimeDataSource,
): JikanPagingDataSource<Anime>() {
    override suspend fun fetchData(page: Int, limit: Int): PaginationItems<Anime> {
        var paginationItems: PaginationItems<Anime> = PaginationItems(
            data = emptyList(),
            hasNextPage = false,
            totalCount = 0
        )

        remoteAnimeDataSource.getTopAnime(
            type = AnimeType.EMPTY,
            filter = AnimeFilter.UPCOMING,
            page, limit
        ).onSuccess { result ->
            paginationItems = result.toPaging()
        }

        return paginationItems
    }
}