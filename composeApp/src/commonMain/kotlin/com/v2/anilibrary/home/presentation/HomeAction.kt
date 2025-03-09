package com.v2.anilibrary.home.presentation

sealed interface HomeAction {
    data class OnTabSelected(val index: Int): HomeAction
}