package com.v2.anilibrary.anime.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.LazyPagingItems

@Composable
fun <T : Any> VerticalGridPagingList(
    header: @Composable () -> Unit,
    data: LazyPagingItems<T>,
    grid: Int = 2,
    gridPadding: PaddingValues,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    modifier: Modifier = Modifier,
    content: @Composable (T?) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(grid),
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement,
        contentPadding = gridPadding,
        modifier = modifier
            .fillMaxSize()
    ) {
        item(span = { GridItemSpan(2) }) {
            header()
        }

        items(data.itemCount) { index ->
            val item = data[index]
            content(item)
        }

        data.loadState.apply {
            when {
                refresh is LoadStateNotLoading && data.itemCount < 1 -> {}

                refresh is LoadStateLoading -> {
                    item {
                        AnimePortraitVariantSkeleton()
                    }
                    item {
                        AnimePortraitVariantSkeleton()
                    }
                }

                append is LoadStateLoading -> {
                    item {
                        AnimePortraitVariantSkeleton()
                    }
                    item {
                        AnimePortraitVariantSkeleton()
                    }
                }

                refresh is LoadStateError -> {
                    item {
                        ErrorView(
                            message = "Unknown Error",
                            onClickRetry = { data.retry() },
                            modifier = Modifier.fillMaxWidth(1f)
                        )
                    }
                }

                append is LoadStateError -> {
                    item {
                        ErrorView(
                            message = "Unknown Error",
                            onClickRetry = { data.retry() },
                            modifier = Modifier.fillMaxWidth(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ErrorView(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp).onPlaced { _ ->
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.Red
        )
        OutlinedButton(
            onClick = onClickRetry, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Text(text = "Try again")
        }
    }
}