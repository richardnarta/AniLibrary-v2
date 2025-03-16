package com.v2.anilibrary.anime.presentation.home

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NavDrawerState(
    val drawerState: DrawerState,
    private val scope: CoroutineScope
) {
    fun onMenuClick() {
        scope.launch {
            if (drawerState.isClosed) {
                drawerState.open()
            } else {
                drawerState.close()
            }
        }
    }

    fun onItemSelected() {}

    fun onBackPress() {
        if (drawerState.isOpen) {
            scope.launch {
                drawerState.close()
            }
        }
    }
}

@Composable
fun rememberNavDrawerState(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    coroutinesScope: CoroutineScope = rememberCoroutineScope()
): NavDrawerState =
    remember(drawerState, coroutinesScope) {
        NavDrawerState(drawerState, coroutinesScope)
    }