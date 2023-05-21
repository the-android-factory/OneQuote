package com.androidfactory.onequote.network

import com.androidfactory.onequote.AppState
import com.androidfactory.onequote.domain.mappers.QuoteMapper
import com.androidfactory.onequote.network.models.NetworkQuote
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val quoteService: QuoteService,
    private val quoteMapper: QuoteMapper
) {

    suspend fun getQuoteOfTheDay(): AppState.Quote? {
        val networkQuote: NetworkQuote? = quoteService.getQuoteOfTheDay().body()?.firstOrNull()
        return networkQuote?.let { quoteMapper.buildFrom(it) }
    }
}