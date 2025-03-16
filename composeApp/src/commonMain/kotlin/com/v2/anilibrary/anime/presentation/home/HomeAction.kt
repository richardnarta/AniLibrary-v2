package com.v2.anilibrary.anime.presentation.home

sealed interface HomeAction {
    data class OnTabSelected(val index: Int): HomeAction
    data class OnTopAiringAnimeSwitched(val index: Int): HomeAction
}