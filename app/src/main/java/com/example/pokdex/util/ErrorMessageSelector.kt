package com.example.pokdex.util

import android.content.res.Resources
import com.example.domain.util.ErrorEntity
import com.example.pokdex.R

fun selectErrorMessageFromErrorEntity(error: ErrorEntity): String {
    return when (error) {
        is ErrorEntity.Network -> Resources.getSystem().getString(R.string.network_error_message)
        is ErrorEntity.NotFound -> Resources.getSystem().getString(R.string.not_found_error_message)
        is ErrorEntity.AccessDenied -> Resources.getSystem().getString(R.string.access_denied_error_message)
        is ErrorEntity.ServiceUnavailable -> Resources.getSystem().getString(R.string.service_unavailable_error_message)
        is ErrorEntity.RequestTimedOut -> Resources.getSystem().getString(R.string.request_timed_out_error_message)
        is ErrorEntity.Unknown -> Resources.getSystem().getString(R.string.unkown_error_message)
        else -> Resources.getSystem().getString(R.string.unkown_error_message)
    }
}
