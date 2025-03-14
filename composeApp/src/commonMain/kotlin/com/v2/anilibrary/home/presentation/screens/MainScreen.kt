package com.v2.anilibrary.home.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.v2.anilibrary.SharedRes
import com.v2.anilibrary.core.components.SearchBar
import com.v2.anilibrary.core.utils.BackPressHandler
import com.v2.anilibrary.home.domain.mainScreenMenu
import com.v2.anilibrary.home.presentation.HomeAction
import com.v2.anilibrary.home.presentation.HomeState
import com.v2.anilibrary.home.presentation.NavDrawerState
import dev.icerock.moko.resources.compose.stringResource


@Composable
fun HomeMainScreen(
    state: HomeState,
    drawerState: NavDrawerState,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier
) {
    BackPressHandler(drawerState.drawerState.isOpen) {
        drawerState.onBackPress()
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
                    }
                }
            )
        }
    )
}