package com.v2.anilibrary.home.presentation

import com.v2.anilibrary.core.presentation.UiText

data class HomeState(
    val selectedTabIndex: Int = 1,
    val errorMessage: UiText? = null,
)