package com.v2.anilibrary.anime.presentation.home.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import anilibrary.composeapp.generated.resources.Res
import anilibrary.composeapp.generated.resources.home_collection
import anilibrary.composeapp.generated.resources.home_main
import anilibrary.composeapp.generated.resources.home_news
import anilibrary.composeapp.generated.resources.ic_collection_24
import anilibrary.composeapp.generated.resources.ic_home_24
import anilibrary.composeapp.generated.resources.ic_news_24
import com.v2.anilibrary.anime.presentation.home.HomeAction
import com.v2.anilibrary.anime.presentation.home.HomeState
import com.v2.anilibrary.anime.presentation.home.HomeViewModel
import com.v2.anilibrary.anime.presentation.home.rememberNavDrawerState
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is HomeAction.OnTabSelected -> Unit
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState (initialPage = 0) { 3 }

    val tabPositions = remember { mutableStateListOf<TabPosition>() }
    val coroutineScope = rememberCoroutineScope()
    val startAnimate = remember { Animatable(0f) }
    val endAnimate = remember { Animatable(0f) }
    val tabColor = MaterialTheme.colorScheme.primary
    val tabIndicatorOffset = 12

    val density = LocalDensity.current

    val drawerState = rememberNavDrawerState()

    LaunchedEffect(state.selectedTabIndex, pagerState.currentPage) {
        if (tabPositions.isNotEmpty()) {
            val newStart = tabPositions[state.selectedTabIndex].left
            val newEnd = tabPositions[state.selectedTabIndex].right

            with(density) {
                coroutineScope.launch {
                    launch {
                        startAnimate.animateTo(
                            newStart.toPx(),
                            animationSpec = spring(dampingRatio = 0.8f, stiffness = 200f)
                        )
                    }

                    launch {
                        endAnimate.animateTo(
                            newEnd.toPx(),
                            animationSpec = spring(dampingRatio = 0.7f, stiffness = 200f)
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        if (state.selectedTabIndex != pagerState.currentPage) {
            onAction(HomeAction.OnTabSelected(pagerState.currentPage))
        }
    }

    Scaffold (
        bottomBar = {
            AnimatedVisibility(
                visible = drawerState.drawerState.isClosed,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box (
                    modifier = modifier
                        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                ) {
                    Box(
                        modifier = modifier
                            .background(
                                color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.93F),
                                shape = RoundedCornerShape(30.dp),
                            )
                            .blur(8.dp)
                            .matchParentSize()
                    )

                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = modifier
                            .background(Color.Transparent)
                    ) {
                        TabRow(
                            selectedTabIndex = state.selectedTabIndex,
                            indicator = { tabPositionsList ->
                                tabPositions.clear()
                                tabPositions.addAll(tabPositionsList)

                                Box(
                                    modifier = modifier
                                        .fillMaxSize()
                                ) {
                                    Canvas(
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        drawRoundRect(
                                            color = tabColor.copy(alpha = 0.5F),
                                            cornerRadius = CornerRadius(20.dp.toPx()),
                                            topLeft = Offset(
                                                startAnimate.value + tabIndicatorOffset,
                                                0f + tabIndicatorOffset
                                            ),
                                            size = Size(
                                                endAnimate.value - startAnimate.value - tabIndicatorOffset*2,
                                                size.height - tabIndicatorOffset*2
                                            )
                                        )
                                    }
                                }
                            },
                            divider = {},
                            containerColor = Color.Transparent,
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            CompositionLocalProvider(LocalRippleConfiguration provides null) {
                                Tab(
                                    selected = state.selectedTabIndex == 0,
                                    onClick = {
                                        coroutineScope.launch {
                                            onAction(HomeAction.OnTabSelected(0))
                                            pagerState.animateScrollToPage(0)
                                        }
                                    },
                                    modifier = modifier
                                        .weight(1f)
                                ) {
                                    Image(
                                        painterResource(Res.drawable.ic_home_24),
                                        contentDescription = stringResource(Res.string.home_main),
                                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                                        modifier = modifier
                                            .padding(top = 12.dp, bottom = 12.dp)
                                            .size(28.dp)
                                    )
                                }

                                Tab(
                                    selected = state.selectedTabIndex == 1,
                                    onClick = {
                                        coroutineScope.launch {
                                            onAction(HomeAction.OnTabSelected(1))
                                            pagerState.animateScrollToPage(1)
                                        }
                                    },
                                    modifier = modifier
                                        .weight(1f)
                                ) {
                                    Image(
                                        painterResource(Res.drawable.ic_news_24),
                                        contentDescription = stringResource(Res.string.home_news),
                                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                                        modifier = modifier
                                            .padding(top = 12.dp, bottom = 12.dp)
                                            .size(28.dp)

                                    )
                                }

                                Tab(
                                    selected = state.selectedTabIndex == 2,
                                    onClick = {
                                        coroutineScope.launch {
                                            onAction(HomeAction.OnTabSelected(2))
                                            pagerState.animateScrollToPage(2)
                                        }
                                    },
                                    modifier = modifier
                                        .weight(1f)
                                ) {
                                    Image(
                                        painterResource(Res.drawable.ic_collection_24),
                                        contentDescription = stringResource(Res.string.home_collection),
                                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                                        modifier = modifier
                                            .padding(top = 12.dp, bottom = 12.dp)
                                            .size(28.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        modifier = modifier
            .fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = modifier
                .fillMaxSize()
        ) { pageIndex ->
            Box(
                modifier = modifier
                    .fillMaxSize()
            ) {
                when (pageIndex) {
                    0 -> HomeMainScreen(state, drawerState, onAction)
                    1 -> HomeNewsScreen()
                    2 -> HomeArchiveScreen()
                }
            }
        }
    }
}