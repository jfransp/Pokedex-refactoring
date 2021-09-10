package com.example.pokdex.util

import android.content.Context
import com.example.domain.util.ErrorEntity
import com.example.pokdex.R

fun selectErrorMessageFromErrorEntity(context: Context, error: ErrorEntity): String {
    return when (error) {
        is ErrorEntity.Network -> context.getString(R.string.network_error_message)
        is ErrorEntity.NotFound -> context.getString(R.string.not_found_error_message)
        is ErrorEntity.AccessDenied -> context.getString(R.string.access_denied_error_message)
        is ErrorEntity.ServiceUnavailable -> context.getString(R.string.service_unavailable_error_message)
        is ErrorEntity.RequestTimedOut -> context.getString(R.string.request_timed_out_error_message)
        is ErrorEntity.Unknown -> context.getString(R.string.unkown_error_message)
        else -> context.getString(R.string.unkown_error_message)
    }
}
