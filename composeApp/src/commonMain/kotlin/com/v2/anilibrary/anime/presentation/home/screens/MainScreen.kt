package com.v2.anilibrary.anime.presentation.home.screens

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import anilibrary.composeapp.generated.resources.Res
import anilibrary.composeapp.generated.resources.home_main
import com.v2.anilibrary.SharedRes
import com.v2.anilibrary.anime.domain.mainScreenMenu
import com.v2.anilibrary.anime.presentation.components.AnimeLandscapeVariant
import com.v2.anilibrary.anime.presentation.components.AnimeLandscapeVariantSkeleton
import com.v2.anilibrary.anime.presentation.components.AnimePortraitVariant
import com.v2.anilibrary.anime.presentation.components.AnimePortraitVariantSkeleton
import com.v2.anilibrary.anime.presentation.components.MoreAnimePortraitVariant
import com.v2.anilibrary.anime.presentation.home.HomeAction
import com.v2.anilibrary.anime.presentation.home.HomeState
import com.v2.anilibrary.anime.presentation.home.NavDrawerState
import com.v2.anilibrary.core.presentation.components.HeadingTitle
import com.v2.anilibrary.core.presentation.components.SearchBar
import com.v2.anilibrary.core.presentation.theme.isDarkTheme
import com.v2.anilibrary.core.presentation.theme.skeletonDark
import com.v2.anilibrary.core.presentation.theme.skeletonLight
import com.v2.anilibrary.core.utils.BackPressHandler
import com.v2.anilibrary.core.utils.formatSeason
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeMainScreen(
    state: HomeState,
    drawerState: NavDrawerState,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState (initialPage = 0) { Int.MAX_VALUE / 2 }

    BackPressHandler(drawerState.drawerState.isOpen) {
        drawerState.onBackPress()
    }

    LaunchedEffect(pagerState.currentPage) {
        if (state.topAiringAnimeResults.isNotEmpty()) {
            if (state.selectedTopAiringAnime != pagerState.currentPage % state.topAiringAnimeResults.size) {
                onAction(HomeAction.OnTopAiringAnimeSwitched(pagerState.currentPage % state.topAiringAnimeResults.size))
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState.drawerState,
        gesturesEnabled = drawerState.drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .widthIn(max = 250.dp)
            ) {
                Text(
                    text = "AniLibrary",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 28.dp).padding(top = 32.dp)
                )
                Spacer(Modifier.height(16.dp))
                mainScreenMenu.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.title) },
                        selected = false,
                        onClick = {
                            drawerState.onItemSelected()
                        },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        },
        content = {
            Scaffold(
                content = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = modifier
                            .fillMaxWidth()
                            .widthIn(max = 400.dp)
                    ) {
                        SearchBar(
                            query = "",
                            onQueryChange = {},
                            placeholder = stringResource(SharedRes.strings.search_anime_hint_default),
                            onImeSearch = {},
                            onIconClicked = drawerState::onMenuClick,
                            isEnabled = false,
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                                .padding(bottom = 96.dp)
                        ) {
                            HeadingTitle(
                                text = stringResource(SharedRes.strings.home_heading_top_airing)
                            )

                            Box {
                                androidx.compose.animation.AnimatedVisibility(
                                    visible = (!state.topAiringIsLoading && !state.topAiringIsError),
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    HorizontalPager(
                                        state = pagerState,
                                        modifier = modifier
                                            .padding(top = 16.dp)
                                    ) { pageIndex ->
                                        AnimeLandscapeVariant(
                                            anime = state.topAiringAnimeResults[pageIndex % state.topAiringAnimeResults.size]
                                        )
                                    }
                                }

                                androidx.compose.animation.AnimatedVisibility(
                                    visible = (state.topAiringIsLoading && !state.topAiringIsError),
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    AnimeLandscapeVariantSkeleton()
                                }
                            }

                            Spacer(modifier = modifier.padding(top = 16.dp))

                            Surface(
                                modifier = modifier
                                    .height(10.dp)
                            ) {
                                TabRow(
                                    selectedTabIndex = if (state.topAiringAnimeResults.isEmpty()) -1 else state.selectedTopAiringAnime,
                                    indicator = {},
                                    divider = {},
                                    containerColor = Color.Transparent,
                                    modifier = modifier
                                        .widthIn(max = 120.dp)
                                ) {
                                    CompositionLocalProvider(LocalRippleConfiguration provides null) {
                                        for (i in 0 until if (state.topAiringAnimeResults.isEmpty()) 10 else state.topAiringAnimeResults.size) {
                                            Tab(
                                                selected = false,
                                                onClick = {},
                                                modifier = modifier
                                                    .weight(1f)
                                            ) {
                                                if (state.selectedTopAiringAnime == i && state.topAiringAnimeResults.isNotEmpty()) {
                                                    Image(
                                                        painter = painterResource(SharedRes.images.ic_pager_indicator_on),
                                                        contentDescription = org.jetbrains.compose.resources.stringResource(
                                                            Res.string.home_main),
                                                        colorFilter = ColorFilter.tint(if (isDarkTheme()) skeletonLight else skeletonDark),
                                                        modifier = modifier
                                                            .padding(horizontal = 3.dp)
                                                            .size(6.dp)
                                                    )
                                                } else {
                                                    Image(
                                                        painter = painterResource(SharedRes.images.ic_pager_indicator_off),
                                                        contentDescription = org.jetbrains.compose.resources.stringResource(
                                                            Res.string.home_main),
                                                        colorFilter = ColorFilter.tint(if (isDarkTheme()) skeletonLight else skeletonDark),
                                                        modifier = modifier
                                                            .padding(horizontal = 3.dp)
                                                            .size(6.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = modifier.padding(top = 32.dp))

                            HeadingTitle(
                                text = stringResource(
                                    SharedRes.strings.home_heading_current_season,
                                    if (
                                        state.seasonAnime.isNullOrEmpty()
                                    ) stringResource(SharedRes.strings.this_season) else state.seasonAnime.formatSeason()
                                )
                            )

                            Spacer(modifier = modifier.padding(top = 16.dp))

                            Box {
                                androidx.compose.animation.AnimatedVisibility(
                                    visible = (!state.seasonAnimeIsLoading && !state.seasonAnimeIsError),
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    LazyRow(
                                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                                        contentPadding = PaddingValues(horizontal = 16.dp),
                                        modifier = modifier
                                            .fillMaxWidth()
                                    ) {
                                        items(7) { index ->
                                            if (index == 6) {
                                                MoreAnimePortraitVariant(
                                                    arrayListOf(
                                                        state.seasonAnimeResults[state.seasonAnimeResults.size - 4].images,
                                                        state.seasonAnimeResults[state.seasonAnimeResults.size - 3].images,
                                                        state.seasonAnimeResults[state.seasonAnimeResults.size - 2].images,
                                                        state.seasonAnimeResults[state.seasonAnimeResults.size - 1].images
                                                    )
                                                )
                                            } else {
                                                AnimePortraitVariant(
                                                    state.seasonAnimeResults[index]
                                                )
                                            }
                                        }
                                    }
                                }

                                androidx.compose.animation.AnimatedVisibility(
                                    visible = (state.seasonAnimeIsLoading && !state.seasonAnimeIsError),
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    LazyRow(
                                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                                        contentPadding = PaddingValues(horizontal = 16.dp),
                                        modifier = modifier
                                            .fillMaxWidth()
                                    ) {
                                        items(6) {
                                            AnimePortraitVariantSkeleton()
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = modifier.padding(top = 32.dp))

                            HeadingTitle(
                                text = stringResource(SharedRes.strings.home_heading_top_rating)
                            )

                            Spacer(modifier = modifier.padding(top = 16.dp))

                            Box {
                                androidx.compose.animation.AnimatedVisibility(
                                    visible = (!state.topRatingIsLoading && !state.topRatingIsError),
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    LazyRow(
                                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                                        contentPadding = PaddingValues(horizontal = 16.dp),
                                        modifier = modifier
                                            .fillMaxWidth()
                                    ) {
                                        items(7) { index ->
                                            if (index == 6) {
                                                MoreAnimePortraitVariant(
                                                    arrayListOf(
                                                        state.topRatingAnimeResults[state.topRatingAnimeResults.size - 4].images,
                                                        state.topRatingAnimeResults[state.topRatingAnimeResults.size - 3].images,
                                                        state.topRatingAnimeResults[state.topRatingAnimeResults.size - 2].images,
                                                        state.topRatingAnimeResults[state.topRatingAnimeResults.size - 1].images
                                                    )
                                                )
                                            } else {
                                                AnimePortraitVariant(
                                                    state.topRatingAnimeResults[index]
                                                )
                                            }
                                        }
                                    }
                                }

                                androidx.compose.animation.AnimatedVisibility(
                                    visible = (state.topRatingIsLoading && !state.topRatingIsError),
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    LazyRow(
                                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                                        contentPadding = PaddingValues(horizontal = 16.dp),
                                        modifier = modifier
                                            .fillMaxWidth()
                                    ) {
                                        items(6) {
                                            AnimePortraitVariantSkeleton()
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = modifier.padding(top = 32.dp))

                            HeadingTitle(
                                text = stringResource(SharedRes.strings.home_heading_upcoming)
                            )

                            Spacer(modifier = modifier.padding(top = 16.dp))
                        }
                    }
                }
            )
        }
    )
}