package com.androidfactory.onequote.network

import androidx.compose.runtime.Composable

sealed interface NetworkOperation<T> {
    data class Loading<T>(val placeholder: T? = null): NetworkOperation<T>
    data class Success<T>(val data: T): NetworkOperation<T>
    data class Failure<T>(val reason: String): NetworkOperation<T>

    // region Functional Programming
    @Composable
    fun onSuccess(action: @Composable (data: T) -> Unit): NetworkOperation<T> {
        if (this is Success) { action(this.data) }
        return this
    }

    @Composable
    fun onFailure(action: @Composable (errorReason: String) -> Unit): NetworkOperation<T> {
        if (this is Failure) { action(this.reason) }
        return this
    }

    @Composable
    fun onLoading(action: @Composable (placeholder: T?) -> Unit): NetworkOperation<T> {
        if (this is Loading) { action(this.placeholder) }
        return this
    }
    // endregion
}