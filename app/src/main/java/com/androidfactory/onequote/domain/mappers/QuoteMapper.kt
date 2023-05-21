package com.androidfactory.onequote.domain.mappers

import com.androidfactory.onequote.AppState
import com.androidfactory.onequote.network.models.NetworkQuote
import javax.inject.Inject

class QuoteMapper @Inject constructor() {

    fun buildFrom(networkQuote: NetworkQuote): AppState.Quote {
        return AppState.Quote(
            displayText = networkQuote.q,
            author = networkQuote.a,
            isFavorite = false
        )
    }
}