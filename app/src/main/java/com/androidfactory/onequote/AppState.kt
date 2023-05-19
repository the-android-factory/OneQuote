package com.androidfactory.onequote

import androidx.compose.ui.graphics.Color

/**
 * Represents the overall state of the app. This data is the source of truth
 * for our Composables to operate off of to render the correct UI.
 */
data class AppState(
    val navigation: Navigation
) {
    data class Navigation(
        val navItems: List<Page>,
        val selectedPage: Page
    ) {
        data class Page(val title: String, val color: Color)
    }

    companion object {
        fun initial(): AppState {
            val pages = buildList {
                add(Navigation.Page("Page 1", Color.Red))
                add(Navigation.Page("Page 2", Color.Green))
                add(Navigation.Page("Page 3", Color.Blue))
            }
            return AppState(
                navigation = Navigation(
                    navItems = pages,
                    selectedPage = pages[0]
                )
            )
        }
    }
}