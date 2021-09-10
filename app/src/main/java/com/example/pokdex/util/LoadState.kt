package com.example.pokdex.util

import com.example.domain.util.ErrorEntity

sealed class LoadState {
    object SUCCESS: LoadState()
    data class ERROR(val error: ErrorEntity): LoadState()
}
