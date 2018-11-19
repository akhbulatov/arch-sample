package com.akhbulatov.githubsample.ui.global

import android.content.Context
import com.akhbulatov.githubsample.R
import retrofit2.HttpException
import java.io.IOException

class ErrorHandler(private val context: Context) {

    fun proceed(error: Throwable, messageListener: (String) -> Unit) {
        messageListener(error.userMessage(context))
    }
}

fun Throwable.userMessage(context: Context): String {
    val errorResId = when (this) {
        is HttpException -> when (this.code()) {
            400 -> R.string.error_bad_request
            403 -> R.string.error_forbidden
            404 -> R.string.error_not_found
            405 -> R.string.error_method_not_allowed
            500 -> R.string.error_server
            else -> R.string.error_unknown
        }

        is IOException -> R.string.error_network
        else -> R.string.error_unknown
    }
    return context.getString(errorResId)
}