package com.v2.anilibrary.anime.presentation.detail.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import anilibrary.composeapp.generated.resources.Res
import anilibrary.composeapp.generated.resources.ic_news_24
import com.v2.anilibrary.SharedRes
import com.v2.anilibrary.anime.domain.AnimeFilterType
import com.v2.anilibrary.anime.presentation.components.AnimeReviewItem
import com.v2.anilibrary.anime.presentation.components.AnimeThemeSong
import com.v2.anilibrary.anime.presentation.components.AnimeTrailerPreview
import com.v2.anilibrary.anime.presentation.components.AnimeWithBadge
import com.v2.anilibrary.anime.presentation.detail.DetailAction
import com.v2.anilibrary.anime.presentation.detail.DetailState
import com.v2.anilibrary.anime.presentation.detail.DetailViewModel
import com.v2.anilibrary.core.presentation.components.RectangleFilterItem
import com.v2.anilibrary.core.presentation.components.ScrimBackground
import com.v2.anilibrary.core.utils.ImageLoader
import com.v2.anilibrary.core.utils.formatNumber
import dev.icerock.moko.resources.compose.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailScreenRoot(
    animeId: Int,
    viewModel: DetailViewModel = koinViewModel<DetailViewModel>()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (
        state.detailAnimeResult == null
    ) {
        viewModel.getAnimeDetail(animeId)
    }

    DetailScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is DetailAction.LastScrollPosition -> Unit
                is DetailAction.OverflowSynopsis -> Unit
                is DetailAction.ViewMoreSynopsis -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailScreen(
    state: DetailState,
    onAction: (DetailAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(Unit) {
        scrollState.scrollTo(state.scrollPosition)
    }

    DisposableEffect(Unit) {
        onDispose {
            onAction(DetailAction.LastScrollPosition(scrollState.value))
        }
    }

    if (
        state.detailAnimeResult != null &&
        state.animePictureResult != null &&
        state.animeCharacterResult != null
    ) {
        val anime = state.detailAnimeResult
        val character = state.animeCharacterResult
        val trailer = state.animeTrailerVideoResult
        val review = state.animeReviewResult

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(vertical = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = anime.title,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    lineHeight = 26.sp
                )

                Text(
                    text = anime.enTitle.toString(),
                    color = MaterialTheme.colorScheme.onBackground.copy(0.8F),
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier
                        .padding(top = 2.dp)
                )

                Text(
                    text = anime.jpTitle.toString(),
                    color = MaterialTheme.colorScheme.onBackground.copy(0.8F),
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier
                        .padding(top = 2.dp)
                )
            }

            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                ScrimBackground(
                    imageUrl = state.animePictureResult.ifEmpty { anime.images.toString() },
                    top = true,
                    bottom = true,
                    start = false,
                    end = false,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .widthIn(max=500.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(horizontal = 16.dp)
                ) {
                    AnimeWithBadge(
                        topBadge = anime.airingSeason!!,
                        bottomBadge = anime.status,
                        imageUrl = anime.images!!,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )

                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1F)
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = "MAL Score",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            lineHeight = 16.sp
                        )

                        Row(
                            modifier = Modifier
                                .padding(top = 8.dp)
                        ) {
                            Surface(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                            ) {
                                Text(
                                    text = if (anime.rating != null) anime.rating.toString() else "No rating",
                                    color = MaterialTheme.colorScheme.background,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                    lineHeight = 14.sp,
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.primary)
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }

                            Text(
                                text = if (anime.ratingUserCount != null) "from ${formatNumber(anime.ratingUserCount)} users" else "",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Medium,
                                fontSize = 11.sp,
                                lineHeight = 11.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 4.dp)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .padding(top = 16.dp)
                        ) {
                            Image(
                                painter = painterResource(SharedRes.images.ic_trophy_24),
                                contentDescription = "Rank",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.CenterVertically)
                            )

                            Text(
                                text = if (anime.rank != null) formatNumber(anime.rank) else "Unranked",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                lineHeight = 16.sp,
                                modifier = Modifier
                                    .padding(start = 12.dp)
                                    .align(Alignment.CenterVertically)
                            )

                            if (anime.rank != null) {
                                Text(
                                    text = "overall",
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 11.sp,
                                    lineHeight = 11.sp,
                                    modifier = Modifier
                                        .padding(start = 4.dp)
                                        .align(Alignment.CenterVertically)
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .padding(top = 12.dp)
                        ) {
                            Image(
                                painter = painterResource(SharedRes.images.ic_calendar_24),
                                contentDescription = "Airing date",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.CenterVertically)
                            )

                            Text(
                                text = anime.airingDate ?: "TBA",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                lineHeight = 15.sp,
                                modifier = Modifier
                                    .padding(start = 12.dp)
                                    .align(Alignment.CenterVertically)
                            )
                        }

                        val type = if (!anime.type.isNullOrEmpty() && anime.type in listOf("TV", "ONA", "OVA", "tv", "ona", "ova")) {
                            anime.type.uppercase()
                        } else if (anime.type.isNullOrEmpty()) {
                            "Unknown"
                        } else {
                            anime.type.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                        }

                        val episodes = if (anime.episodeCount != null) " (${anime.episodeCount} Eps)" else " (? Eps)"

                        Row(
                            modifier = Modifier
                                .padding(top = 12.dp)
                        ) {
                            Image(
                                painter = painterResource(SharedRes.images.ic_play_24),
                                contentDescription = "Type",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.CenterVertically)
                            )

                            Text(
                                text = if (anime.type == "movie" || anime.type.isNullOrEmpty()) type else type + episodes,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                lineHeight = 15.sp,
                                modifier = Modifier
                                    .padding(start = 12.dp)
                                    .align(Alignment.CenterVertically)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .padding(top = 12.dp)
                        ) {
                            Image(
                                painter = painterResource(SharedRes.images.ic_time_24),
                                contentDescription = "Duration",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.CenterVertically)
                            )

                            Text(
                                text = anime.duration ?: "Unknown",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                lineHeight = 15.sp,
                                modifier = Modifier
                                    .padding(start = 12.dp)
                                    .align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }

            Text(
                text = "Genres & Themes",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            val genreTheme = mutableListOf<AnimeFilterType>()

            anime.genres!!.map {
                genreTheme.add(
                    AnimeFilterType(
                        type = "genre",
                        text = it
                    )
                )
            }

            anime.themes!!.map {
                genreTheme.add(
                    AnimeFilterType(
                        type = "theme",
                        text = it
                    )
                )
            }

            LazyRow(
                contentPadding = PaddingValues(end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            ) {
                items(
                    count = genreTheme.size
                ) { index ->
                    RectangleFilterItem(
                        text = genreTheme[index].text,
                        textSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        outlined = genreTheme[index].type == "theme"
                    )
                }
            }

            Text(
                text = "Studios & Producers",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
            )

            val studioProducer = mutableListOf<AnimeFilterType>()

            anime.studios!!.map {
                studioProducer.add(
                    AnimeFilterType(
                        type = "studio",
                        text = it
                    )
                )
            }

            anime.producers!!.map {
                studioProducer.add(
                    AnimeFilterType(
                        type = "producer",
                        text = it
                    )
                )
            }

            LazyRow(
                contentPadding = PaddingValues(end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            ) {
                items(
                    count = studioProducer.size
                ) { index ->
                    RectangleFilterItem(
                        text = studioProducer[index].text,
                        textSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        outlined = studioProducer[index].type == "producer"
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Synopsis",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier
                        .padding(top = 24.dp)
                )

                Text(
                    text = anime.synopsis.toString(),
                    color = MaterialTheme.colorScheme.onBackground.copy(0.8F),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    textAlign = TextAlign.Justify,
                    maxLines = if (state.synopsisIsExpanded) Int.MAX_VALUE else 10,
                    onTextLayout = { textLayoutResult ->
                        onAction(DetailAction.OverflowSynopsis(textLayoutResult.hasVisualOverflow))
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                )

                if (state.synopsisIsExpanded || state.synopsisIsOverflowed) {
                    Text(
                        text = if (state.synopsisIsExpanded) "Read Less" else "Read More",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable { onAction(DetailAction.ViewMoreSynopsis(state.synopsisIsExpanded)) }
                    )
                }
            }

            if (character.isNotEmpty()) {
                Text(
                    text = "Characters",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 24.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(end = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = modifier
                        .padding(top = 16.dp)
                        .padding(start = 16.dp)
                        .fillMaxWidth()
                ) {
                    items(
                        count = character.count { it.role == "Main" },
                        key = { genre -> genre }
                    ) { index ->
                        if (character[index].role == "Main") {
                            Column(
                                modifier = Modifier
                                    .width(90.dp)
                            ) {
                                OutlinedCard(
                                    shape = RoundedCornerShape(30.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(0.75F)
                                ) {
                                    val userImage = ImageLoader(imageUrl = character[index].images)

                                    when(userImage.result) {
                                        null -> {
                                            Image(
                                                painter = painterResource(SharedRes.images.placeholder_portrait),
                                                contentDescription = character[index].name,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .fillMaxSize()
                                            )
                                        }
                                        else -> {
                                            Image(
                                                painter = if (userImage.result.isSuccess) userImage.painter else painterResource(
                                                    SharedRes.images.placeholder_error_portrait),
                                                contentDescription =  character[index].name,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .fillMaxSize()
                                            )
                                        }
                                    }
                                }

                                Text(
                                    text = character[index].name,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp,
                                    lineHeight = 12.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp)
                                        .align(Alignment.CenterHorizontally)
                                )
                            }
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Theme Songs",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier
                        .padding(top = 24.dp)
                )

                anime.songTheme!!.openings!!.map {
                    AnimeThemeSong(
                        type = "Op",
                        song = com.v2.anilibrary.anime.domain.AnimeThemeSong(
                            song = it.song,
                            singer = it.singer
                        ),
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )

                }

                anime.songTheme.endings!!.map {
                    AnimeThemeSong(
                        type = "Ed",
                        song = com.v2.anilibrary.anime.domain.AnimeThemeSong(
                            song = it.song,
                            singer = it.singer
                        ),
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                }
            }

            if (!trailer.isNullOrEmpty()) {
                Text(
                    text = "Promotional Videos",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 24.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(end = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = modifier
                        .padding(top = 16.dp)
                        .padding(start = 16.dp)
                        .fillMaxWidth()
                ) {
                    items(
                        count = trailer.count { it.url.contains("youtube.com") }
                    ) { index ->
                        AnimeTrailerPreview(
                            trailer = trailer[index]
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                if (!review.isNullOrEmpty()) {
                    Text(
                        text = "Reviews",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier
                            .padding(top = 24.dp)
                    )

                    val maxReview = if (review.size > 3) 3 else review.size

                    for (i in 0..< maxReview) {
                        AnimeReviewItem(
                            review = review[i],
                            modifier = Modifier
                                .padding(top = 16.dp)
                        )
                    }
                }

                Text(
                    text = "External Links",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier
                        .padding(top = 24.dp)
                )

                val externalLinks = anime.streamingPlatform!! + anime.externalLinks!!

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    externalLinks.forEach { link ->
                        RectangleFilterItem(
                            text = if (link.name.startsWith("@")) "Twitter" else link.name,
                            textSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            icon = org.jetbrains.compose.resources.painterResource(Res.drawable.ic_news_24),
                            iconColorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.background),
                            onClick = {
                                uriHandler.openUri(link.url)
                            }
                        )
                    }
                }
            }
        }
    }
}