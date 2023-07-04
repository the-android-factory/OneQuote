package com.androidfactory.onequote

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidfactory.onequote.network.NetworkOperation
import com.androidfactory.onequote.ui.theme.DarkPurple
import com.androidfactory.onequote.ui.theme.Purple
import com.androidfactory.onequote.ui.theme.Teal

@Composable
fun DailyQuoteScreen(
    networkOperation: NetworkOperation<AppState.Quote>,
    onFavoriteClicked: (AppState.Quote) -> Unit,
    onRefresh: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        networkOperation.onLoading { placeholderQuote: AppState.Quote? ->
            DailyQuoteContent(
                quoteText = placeholderQuote?.displayText ?: "Loading",
                quoteAuthor = placeholderQuote?.author ?: "The Android Factory",
                isFavorite = placeholderQuote?.isFavorite ?: false,
                onClick = { /* Nothing to do */ }
            )
        }.onSuccess { quote ->
            DailyQuoteContent(
                quoteText = quote.displayText,
                quoteAuthor = quote.author,
                isFavorite = quote.isFavorite,
                onClick = { onFavoriteClicked(quote) }
            )
        }.onFailure { errorReason ->
            DailyQuoteContent(
                quoteText = errorReason,
                quoteAuthor = "The Android Factory",
                isFavorite = false,
                onClick = { /* Nothing to do */ }
            )
        }

        val backgroundColor = animateColorAsState(
            targetValue = when (networkOperation) {
                is NetworkOperation.Failure -> Color.Red
                is NetworkOperation.Loading -> Teal
                is NetworkOperation.Success -> Purple
            }
        )

        Text(
            text = when (networkOperation) {
                is NetworkOperation.Failure -> "Failure"
                is NetworkOperation.Loading -> "Loading..."
                is NetworkOperation.Success -> "Up to date"
            },
            color = DarkPurple,
            modifier = Modifier
                .padding(top = 48.dp)
                .background(
                    color = backgroundColor.value,
                    shape = MaterialTheme.shapes.medium
                )
                .clip(MaterialTheme.shapes.medium)
                .clickable { onRefresh() }
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(CenterHorizontally)
        )
    }
}

@Composable
private fun DailyQuoteContent(
    quoteText: String,
    quoteAuthor: String,
    isFavorite: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(horizontal = 32.dp)
            .background(color = DarkPurple, shape = MaterialTheme.shapes.large)
            .clip(shape = MaterialTheme.shapes.large)
    ) {
        Text(
            text = quoteText,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 32.dp, vertical = 50.dp),
            fontSize = 32.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = quoteAuthor,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(color = Purple)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.End
        )

        Box(
            modifier = Modifier
                .padding(start = 8.dp, bottom = 16.dp)
                .size(60.dp)
                .align(Alignment.BottomStart)
                .background(color = Purple, shape = RoundedCornerShape(50))
                .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(50))
                .clip(RoundedCornerShape(50))
                .clickable { onClick?.invoke() }
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favorite Icon",
                modifier = Modifier.align(Alignment.Center),
                tint = Color.Black
            )
        }
    }
}