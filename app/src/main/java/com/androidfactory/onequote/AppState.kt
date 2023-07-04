package com.androidfactory.onequote

import androidx.compose.ui.graphics.Color
import com.androidfactory.onequote.network.NetworkOperation

/**
 * Represents the overall state of the app. This data is the source of truth
 * for our Composables to operate off of to render the correct UI.
 */
data class AppState(
    val navigation: Navigation,
    val quoteOfTheDay: NetworkOperation<Quote> = NetworkOperation.Loading(),
    val allQuotes: NetworkOperation<List<Quote>> = NetworkOperation.Loading()
) {
    data class Quote(
        val displayText: String,
        val author: String,
        val isFavorite: Boolean
    )

    data class Navigation(
        val navItems: List<Page>,
        val selectedPage: Page
    ) {
        data class Page(val title: String, val color: Color)
    }

    companion object {
        fun initial(): AppState {
            val pages = buildList {
                add(Navigation.Page("All quotes", Color.Red))
                add(Navigation.Page("Daily quote", Color.Green))
                add(Navigation.Page("Favorites", Color.Blue))
            }
            return AppState(
                navigation = Navigation(
                    navItems = pages,
                    selectedPage = pages[1]
                )
            )
        }
    }
}