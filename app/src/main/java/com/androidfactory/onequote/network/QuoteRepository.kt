package com.androidfactory.onequote.network

import com.androidfactory.onequote.network.models.NetworkQuote
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val quoteService: QuoteService
) {

    suspend fun getQuoteOfTheDay(): NetworkQuote? {
        return quoteService.getQuoteOfTheDay().body()?.first()
    }
}