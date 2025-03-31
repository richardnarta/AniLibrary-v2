package com.v2.anilibrary.anime.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import anilibrary.composeapp.generated.resources.Res
import anilibrary.composeapp.generated.resources.ic_star_24
import com.v2.anilibrary.SharedRes
import com.v2.anilibrary.anime.domain.Anime
import com.v2.anilibrary.core.presentation.theme.isDarkTheme
import com.v2.anilibrary.core.presentation.theme.skeletonDark
import com.v2.anilibrary.core.presentation.theme.skeletonLight
import com.v2.anilibrary.core.presentation.theme.starDark
import com.v2.anilibrary.core.presentation.theme.starLight
import com.v2.anilibrary.core.utils.ImageLoader
import com.v2.anilibrary.core.utils.formatNumber
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AnimePortraitVariant(
    anime: Anime,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        shape = RoundedCornerShape(30.dp),
        modifier = modifier
            .widthIn(max = 150.dp)
            .fillMaxWidth()
            .aspectRatio(0.67F)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            val backgroundImage = ImageLoader(imageUrl = anime.images)

            when(backgroundImage.result) {
                null -> {
                    Image(
                        painter = painterResource(SharedRes.images.placeholder_portrait),
                        contentDescription = anime.title,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxSize()
                    )
                }
                else -> {
                    Image(
                        painter = if (backgroundImage.result.isSuccess) backgroundImage.painter else painterResource(
                            SharedRes.images.placeholder_error_portrait),
                        contentDescription = anime.title,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxSize()
                    )
                }
            }

            Column (
                modifier = modifier
                    .fillMaxSize()
            ) {
                Box (
                    contentAlignment = Alignment.TopEnd,
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(3F)
                ) {
                    Card(
                        shape = RoundedCornerShape(15.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.8F)
                        ),
                        modifier = modifier
                            .padding(16.dp)
                            .wrapContentSize()
                    ) {
                        Text(
                            text = if (anime.rank != null) "# ${formatNumber(anime.rank)}" else "Unranked",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = modifier
                                .alpha(0.8F)
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }

                Box (
                    modifier = modifier
                        .weight(1F)
                        .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.9F))
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = modifier
                            .fillMaxSize()
                    ) {
                        Text(
                            text = anime.title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 15.sp,
                            lineHeight = 15.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = modifier
                                .padding(horizontal = 8.dp)
                        )

                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier
                                .padding(horizontal = 8.dp)
                        ) {
                            val episodes = if (!anime.type.isNullOrEmpty() && anime.type in listOf("TV", "ONA", "OVA", "tv", "ona", "ova")) {
                                "${anime.type.uppercase()} " + if (anime.episodeCount != null) "(${anime.episodeCount} Eps)" else "(? Eps)"
                            } else if (anime.type.isNullOrEmpty()) {
                                "?"
                            } else {
                                anime.type.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                            }

                            Text(
                                text = episodes,
                                fontSize = 10.sp,
                                lineHeight = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = modifier
                                    .alpha(0.7F)
                            )

                            Spacer(modifier = modifier.width(8.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    org.jetbrains.compose.resources.painterResource(Res.drawable.ic_star_24),
                                    colorFilter = ColorFilter.tint(
                                        if (isDarkTheme()) starDark else starLight
                                    ),
                                    contentDescription = "",
                                    modifier = modifier
                                        .size(14.dp)
                                )

                                Row(
                                    verticalAlignment = Alignment.Bottom,
                                    modifier = modifier
                                        .padding(start = 4.dp)
                                ) {
                                    Text(
                                        text = if (anime.rating == null) "n/A" else anime.rating.toString(),
                                        fontSize = 10.sp,
                                        lineHeight = 10.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = modifier
                                            .alpha(0.7F)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnimePortraitVariantSkeleton(
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        shape = RoundedCornerShape(30.dp),
        modifier = modifier
            .widthIn(max = 150.dp)
            .fillMaxWidth()
            .aspectRatio(0.67F)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(SharedRes.images.placeholder_portrait),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxSize()
            )

            Column (
                modifier = modifier
                    .fillMaxSize()
            ) {
                Box (
                    contentAlignment = Alignment.TopEnd,
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(3F)
                ) {
                    Card(
                        shape = RoundedCornerShape(15.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.9F)
                        ),
                        modifier = modifier
                            .padding(16.dp)
                            .wrapContentSize()
                    ) {
                        Text(
                            text = "# XXX",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            color = Color.Transparent,
                            fontWeight = FontWeight.SemiBold,
                            modifier = modifier
                                .alpha(0.8F)
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }

                Box (
                    modifier = modifier
                        .weight(1F)
                        .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.9F))
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = "",
                            maxLines = 1,
                            fontSize = 15.sp,
                            lineHeight = 15.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = modifier
                                .fillMaxWidth()
                                .background(
                                    color = if (isDarkTheme()) skeletonDark else skeletonLight,
                                    shape = RoundedCornerShape(50.dp)
                                )
                        )

                        Text(
                            text = "",
                            fontSize = 10.sp,
                            lineHeight = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = modifier
                                .width(100.dp)
                                .background(
                                    color = if (isDarkTheme()) skeletonDark else skeletonLight,
                                    shape = RoundedCornerShape(50.dp)
                                )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MoreAnimePortraitVariant(
    animeImageUrl: ArrayList<String?>,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        shape = RoundedCornerShape(30.dp),
        modifier = modifier
            .widthIn(max = 150.dp)
            .fillMaxWidth()
            .aspectRatio(0.67F)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1F)
                ) {
                    val backgroundImage1 = ImageLoader(imageUrl = animeImageUrl[0])

                    when(backgroundImage1.result) {
                        null -> {
                            Image(
                                painter = painterResource(SharedRes.images.placeholder_portrait),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = modifier
                                    .weight(1F)
                            )
                        }
                        else -> {
                            Image(
                                painter = if (backgroundImage1.result.isSuccess) backgroundImage1.painter else painterResource(
                                    SharedRes.images.placeholder_error_portrait),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = modifier
                                    .weight(1F)
                                    .blur(1.dp)
                            )
                        }
                    }

                    val backgroundImage2 = ImageLoader(imageUrl = animeImageUrl[1])

                    when(backgroundImage2.result) {
                        null -> {
                            Image(
                                painter = painterResource(SharedRes.images.placeholder_portrait),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = modifier
                                    .weight(1F)
                            )
                        }
                        else -> {
                            Image(
                                painter = if (backgroundImage2.result.isSuccess) backgroundImage2.painter else painterResource(
                                    SharedRes.images.placeholder_error_portrait),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = modifier
                                    .weight(1F)
                                    .blur(1.dp)
                            )
                        }
                    }
                }

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1F)
                ) {
                    val backgroundImage1 = ImageLoader(imageUrl = animeImageUrl[2])

                    when(backgroundImage1.result) {
                        null -> {
                            Image(
                                painter = painterResource(SharedRes.images.placeholder_portrait),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = modifier
                                    .weight(1F)
                            )
                        }
                        else -> {
                            Image(
                                painter = if (backgroundImage1.result.isSuccess) backgroundImage1.painter else painterResource(
                                    SharedRes.images.placeholder_error_portrait),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = modifier
                                    .weight(1F)
                                    .blur(1.dp)
                            )
                        }
                    }

                    val backgroundImage2 = ImageLoader(imageUrl = animeImageUrl[3])

                    when(backgroundImage2.result) {
                        null -> {
                            Image(
                                painter = painterResource(SharedRes.images.placeholder_portrait),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = modifier
                                    .weight(1F)
                            )
                        }
                        else -> {
                            Image(
                                painter = if (backgroundImage2.result.isSuccess) backgroundImage2.painter else painterResource(
                                    SharedRes.images.placeholder_error_portrait),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = modifier
                                    .weight(1F)
                                    .blur(1.dp)
                            )
                        }
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .background(
                        color = Color.Black.copy(alpha = 0.8F)
                    )
                    .fillMaxSize()
            ){
                Text(
                    text = stringResource(SharedRes.strings.tap_to_find_more_anime),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                )

                Image(
                    painterResource(SharedRes.images.ic_more_big_24),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                    modifier = modifier
                        .padding(top = 12.dp)
                        .size(32.dp)
                )
            }
        }
    }
}