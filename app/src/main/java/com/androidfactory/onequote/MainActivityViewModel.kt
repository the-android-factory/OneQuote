package com.androidfactory.onequote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidfactory.onequote.AppState.Navigation.Page
import com.androidfactory.onequote.network.NetworkOperation
import com.androidfactory.onequote.network.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val quoteRepository: QuoteRepository
) : ViewModel() {

    private val _appState = MutableStateFlow(AppState.initial())
    val appState: StateFlow<AppState> = _appState.asStateFlow()

    fun selectPage(page: Page) {
        _appState.update {
            return@update it.copy(
                navigation = it.navigation.copy(
                    selectedPage = page
                )
            )
        }
    }

    fun fetchQuoteOfTheDay() = viewModelScope.launch {
        _appState.update { return@update it.copy(quoteOfTheDay = NetworkOperation.Loading()) }
        _appState.update { return@update it.copy(quoteOfTheDay = quoteRepository.getQuoteOfTheDay()) }
    }
}