package com.androidfactory.onequote.network

import com.androidfactory.onequote.AppState
import com.androidfactory.onequote.domain.mappers.QuoteMapper
import com.androidfactory.onequote.network.models.NetworkQuote
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val quoteService: QuoteService,
    private val quoteMapper: QuoteMapper
) {

    suspend fun getQuoteOfTheDay(): NetworkOperation<AppState.Quote> {
        return quoteService.getQuoteOfTheDay().run {
            if (isSuccessful) {
                NetworkOperation.Success(data = quoteMapper.buildFrom(body()!!.first()))
            } else {
                NetworkOperation.Failure(reason = "Unable to fetch quote of the day")
            }
        }
    }
}