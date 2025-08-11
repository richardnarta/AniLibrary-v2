package com.v2.anilibrary.anime.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
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
import com.v2.anilibrary.anime.domain.AnimeReview
import com.v2.anilibrary.core.presentation.theme.isDarkTheme
import com.v2.anilibrary.core.presentation.theme.starDark
import com.v2.anilibrary.core.presentation.theme.starLight
import com.v2.anilibrary.core.utils.ImageLoader
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun AnimeReviewItem(
    review: AnimeReview,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isSpoiler by remember { mutableStateOf(review.spoilerStatus) }
    var isOverflowing by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                val userImage = ImageLoader(imageUrl = review.userProfile)

                when(userImage.result) {
                    null -> {
                        Image(
                            painter = painterResource(SharedRes.images.placeholder_portrait),
                            contentDescription = review.userUsername,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    else -> {
                        Image(
                            painter = if (userImage.result.isSuccess) userImage.painter else painterResource(
                                SharedRes.images.placeholder_error_portrait),
                            contentDescription =  review.userUsername,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            }

            Column (
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = review.userUsername,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                )

                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 2.dp)
                ) {
                    Image(
                        org.jetbrains.compose.resources.painterResource(Res.drawable.ic_star_24),
                        colorFilter = ColorFilter.tint(
                            if (isDarkTheme()) starDark else starLight
                        ),
                        contentDescription = "",
                        modifier = Modifier
                            .size(16.dp)
                    )

                    Text(
                        text = "${review.score}/10",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                        lineHeight = 11.sp,
                        modifier = Modifier
                            .padding(start = 4.dp)
                    )

                    Image(
                        when (review.tag) {
                            "Recommended" -> {
                                painterResource(SharedRes.images.recommended)
                            }
                            "Not Recommended" -> {
                                painterResource(SharedRes.images.not_recommended)
                            }
                            else -> {
                                painterResource(SharedRes.images.mixed_feeling)
                            }
                        },
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 8.dp)
                    )

                    Text(
                        text = review.tag,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                        lineHeight = 11.sp,
                        modifier = Modifier
                            .padding(start = 4.dp)
                    )
                }
            }

            Text(
                text = review.date,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Medium,
                fontSize = 8.sp,
                lineHeight = 8.sp,
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.Top)
            )
        }

        Box(
            modifier = Modifier
                .padding(top = 12.dp)
        ) {
            Text(
                text = review.review,
                color = MaterialTheme.colorScheme.onBackground.copy(0.8F),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 15.sp,
                maxLines = if (isExpanded) Int.MAX_VALUE else 5,
                textAlign = TextAlign.Justify,
                onTextLayout = { textLayoutResult ->
                    isOverflowing = textLayoutResult.hasVisualOverflow
                },
                modifier = Modifier
                    .blur(if (isSpoiler) 6.dp else 0.dp)
            )

            if (isSpoiler) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .matchParentSize()
                        .clickable { isSpoiler = false }
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Spoiler hidden",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Text(
                        text = "This review contains spoilers.\nClick to reveal.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        lineHeight = 15.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

        Text(
            text = if (isExpanded) "Read Less" else "Read More",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.CenterHorizontally)
                .clickable { isExpanded = !isExpanded }
        )

        HorizontalDivider(
            modifier = Modifier.padding(top = 16.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
        )
    }
}