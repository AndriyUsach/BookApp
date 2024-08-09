package com.books.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.app.data.BooksRepository
import com.books.app.data.model.BannerSlide
import com.books.app.data.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val repository: BooksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val data = repository.fetchHomeData()

            val genreToBookList = mutableMapOf<String, PersistentList<Book>>()

            data.books.forEach { book ->
                val list = genreToBookList[book.genre] ?: persistentListOf()
                genreToBookList[book.genre] = list.add(book)

            }
            
            val genres = genreToBookList.map { (genre, books) ->
                GenreWithBooks(genre, books)
            }

            _uiState.value = HomeUiState(
                topBannerSlides = persistentListOf<BannerSlide>().addAll(data.topBannerSlides),
                genres = persistentListOf<GenreWithBooks>().addAll(genres)
            )
        }
    }

}