package com.androidfactory.onequote.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.androidfactory.onequote.AppState
import com.androidfactory.onequote.AppState.Navigation.Page
import com.androidfactory.onequote.thenIf

@Composable
fun HeaderNavigation(
    navigation: AppState.Navigation,
    onClick: (Page) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.large.copy(
                    topStart = CornerSize(0),
                    topEnd = CornerSize(0)
                )
            )
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        navigation.navItems.forEach {
            val isSelected = it.title == navigation.selectedPage.title
            val weight = if (isSelected) FontWeight.Normal else FontWeight.Light
            Text(
                text = it.title,
                fontWeight = weight,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .clickable { onClick(it) }
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = MaterialTheme.shapes.medium
                    )
                    .thenIf(isSelected) {
                        Modifier.border(
                            width = 1.dp,
                            color = MaterialTheme.colors.onSurface,
                            shape = MaterialTheme.shapes.medium
                        )
                    }
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            )
        }
    }
}