package com.v2.anilibrary.anime.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class DetailViewModel: ViewModel() {
    private val _state = MutableStateFlow(DetailState())
    val state = _state
        .onStart {
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: DetailAction) {
        when (action) {
            is DetailAction.LastScrollPosition -> {
                _state.update {
                    it.copy(scrollPosition = action.y)
                }
            }

            is DetailAction.ViewMoreSynopsis -> {
                _state.update {
                    it.copy(
                        synopsisIsExpanded = !action.isExpanded
                    )
                }
            }

            is DetailAction.OverflowSynopsis -> {
                _state.update {
                    it.copy(
                        synopsisIsOverflowed = action.isOverFlowed
                    )
                }
            }
        }
    }
}