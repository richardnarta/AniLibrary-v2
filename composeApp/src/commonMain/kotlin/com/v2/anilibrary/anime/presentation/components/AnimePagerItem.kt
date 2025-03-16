package com.v2.anilibrary.anime.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import anilibrary.composeapp.generated.resources.Res
import anilibrary.composeapp.generated.resources.ic_star_24
import com.v2.anilibrary.SharedRes
import com.v2.anilibrary.anime.domain.Anime
import com.v2.anilibrary.core.presentation.components.RectangleFilterItem
import com.v2.anilibrary.core.presentation.theme.isDarkTheme
import com.v2.anilibrary.core.presentation.theme.skeletonDark
import com.v2.anilibrary.core.presentation.theme.skeletonLight
import com.v2.anilibrary.core.presentation.theme.starDark
import com.v2.anilibrary.core.presentation.theme.starLight
import com.v2.anilibrary.core.utils.ImageLoader
import com.v2.anilibrary.core.utils.formatNumber
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun AnimePagerItem(
    anime: Anime,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        shape = RoundedCornerShape(40.dp),
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .aspectRatio(1.67F)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            val backgroundImage = ImageLoader(imageUrl = anime.images)

            when(backgroundImage.result) {
                null -> {
                    Image(
                        painter = painterResource(SharedRes.images.placeholder_landscape),
                        contentDescription = anime.title,
                        alpha = 0.2F,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxSize()
                            .blur(4.dp)
                    )
                }
                else -> {
                    Image(
                        painter = if (backgroundImage.result.isSuccess) backgroundImage.painter else painterResource(SharedRes.images.placeholder_error_landscape),
                        contentDescription = anime.title,
                        alpha = 0.2F,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxSize()
                            .blur(4.dp)
                    )
                }
            }

            Row(
                modifier = modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp)
                    .padding(start = 10.dp, end = 12.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(35.dp),
                    modifier = modifier
                        .fillMaxHeight()
                        .aspectRatio(0.67F, matchHeightConstraintsFirst = true)
                ) {
                    val coverImage = ImageLoader(imageUrl = anime.images)

                    when(coverImage.result) {
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
                                painter = if (coverImage.result.isSuccess) coverImage.painter else painterResource(SharedRes.images.placeholder_error_portrait),
                                contentDescription = anime.title,
                                contentScale = ContentScale.Crop,
                                modifier = modifier
                                    .fillMaxSize()
                            )
                        }
                    }
                }

                Column(
                    modifier = modifier
                        .weight(1F)
                        .padding(start = 16.dp, end = 8.dp)
                ) {
                    Text(
                        text = anime.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 20.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        text = anime.enTitle ?: "~",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 10.sp,
                        lineHeight = 10.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = modifier
                            .alpha(0.75F)
                            .padding(top = 4.dp)
                    )

                    Row(
                        modifier = modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    ) {
                        if (!anime.genres.isNullOrEmpty()) {
                            for (item in anime.genres) {
                                RectangleFilterItem(text = item)
                            }
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .padding(top = 8.dp)
                    ) {
                        Image(
                            org.jetbrains.compose.resources.painterResource(Res.drawable.ic_star_24),
                            colorFilter = ColorFilter.tint(
                                if (isDarkTheme()) starDark else starLight
                            ),
                            contentDescription = "",
                            modifier = modifier
                        )

                        Row(
                            verticalAlignment = Alignment.Bottom,
                            modifier = modifier
                                .padding(start = 4.dp)
                        ) {
                            Text(
                                text = if (anime.rating == null) "n/A" else anime.rating.toString(),
                                fontSize = 14.sp,
                                lineHeight = 14.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = modifier
                                    .alpha(0.7F)
                            )

                            Text(
                                text = if(anime.ratingUserCount == null) "0 user" else "${formatNumber(anime.ratingUserCount)} users",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 9.sp,
                                lineHeight = 12.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = modifier
                                    .alpha(0.5F)
                                    .padding(start = 4.dp)
                            )
                        }
                    }

                    Text(
                        text = anime.synopsis,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = modifier
                            .alpha(0.5F)
                            .weight(1F)
                            .padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AnimePagerItemSkeleton(
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        shape = RoundedCornerShape(40.dp),
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxWidth()
            .aspectRatio(1.67F)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp)
                    .padding(start = 10.dp, end = 12.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(35.dp),
                    modifier = modifier
                        .fillMaxHeight()
                        .aspectRatio(0.67F, matchHeightConstraintsFirst = true)
                ) {
                    Image(
                        painter = painterResource(SharedRes.images.placeholder_portrait),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxSize()
                    )
                }

                Column(
                    modifier = modifier
                        .weight(1F)
                        .padding(start = 16.dp, end = 8.dp)
                ) {
                    Text(
                        text = "",
                        fontSize = 20.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .fillMaxWidth()
                            .background(
                                color = if (isDarkTheme()) skeletonDark else skeletonLight,
                                shape = RoundedCornerShape(50.dp)
                            )
                    )

                    Spacer(modifier = modifier.padding(2.dp))

                    Text(
                        text = "",
                        fontSize = 20.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .fillMaxWidth()
                            .background(
                                color = if (isDarkTheme()) skeletonDark else skeletonLight,
                                shape = RoundedCornerShape(50.dp)
                            )
                    )

                    Spacer(modifier = modifier.padding(4.dp))

                    Text(
                        text = "",
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = modifier
                            .fillMaxWidth()
                            .background(
                                color = if (isDarkTheme()) skeletonDark else skeletonLight,
                                shape = RoundedCornerShape(50.dp)
                            )
                    )

                    Spacer(modifier = modifier.padding(4.dp))

                    Text(
                        text = "",
                        fontSize = 20.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .fillMaxWidth()
                            .background(
                                color = if (isDarkTheme()) skeletonDark else skeletonLight,
                                shape = RoundedCornerShape(50.dp)
                            )
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .padding(vertical = 8.dp)
                    ) {
                        Image(
                            org.jetbrains.compose.resources.painterResource(Res.drawable.ic_star_24),
                            colorFilter = ColorFilter.tint(
                                if (isDarkTheme()) starDark else starLight
                            ),
                            contentDescription = "",
                            modifier = modifier
                        )

                        Row(
                            verticalAlignment = Alignment.Bottom,
                            modifier = modifier
                                .padding(start = 4.dp)
                        ) {
                            Text(
                                text = "",
                                fontSize = 16.sp,
                                lineHeight = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = modifier
                                    .width(30.dp)
                                    .background(
                                        color = if (isDarkTheme()) skeletonDark else skeletonLight,
                                        shape = RoundedCornerShape(50.dp)
                                    )
                            )

                            Spacer(modifier = modifier.width(4.dp))

                            Text(
                                text = "",
                                fontSize = 10.sp,
                                lineHeight = 10.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = modifier
                                    .width(60.dp)
                                    .background(
                                        color = if (isDarkTheme()) skeletonDark else skeletonLight,
                                        shape = RoundedCornerShape(50.dp)
                                    )
                            )
                        }
                    }

                    for (i in 0..2) {
                        Text(
                            text = "",
                            fontSize = 12.sp,
                            lineHeight = 12.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = modifier
                                .fillMaxWidth()
                                .background(
                                    color = if (isDarkTheme()) skeletonDark else skeletonLight,
                                    shape = RoundedCornerShape(50.dp)
                                )
                        )

                        Spacer(modifier = modifier.padding(2.dp))
                    }
                }
            }
        }
    }
}