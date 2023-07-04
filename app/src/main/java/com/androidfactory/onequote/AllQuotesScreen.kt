package com.androidfactory.onequote

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androidfactory.onequote.components.LoadingComponent
import com.androidfactory.onequote.components.SingleQuoteListItem
import com.androidfactory.onequote.network.NetworkOperation
import com.androidfactory.onequote.ui.theme.Teal

@Composable
fun AllQuotesScreen(
    allQuotesOperation: NetworkOperation<List<AppState.Quote>>,
    onFavoriteClicked: (AppState.Quote) -> Unit
) {
    allQuotesOperation.onLoading {
        LoadingComponent()
    }.onSuccess {
        AllQuotesDisplay(quotes = it, onFavoriteClicked = onFavoriteClicked)
    }.onFailure {
        // todo
    }
}

@Composable
private fun AllQuotesDisplay(
    quotes: List<AppState.Quote>,
    onFavoriteClicked: (AppState.Quote) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 48.dp)
    ) {
        items(items = quotes, key = { it.hashCode() }) {
            SingleQuoteListItem(
                quote = it,
                onFavoriteClicked = { onFavoriteClicked(it) }
            )
        }
    }
}