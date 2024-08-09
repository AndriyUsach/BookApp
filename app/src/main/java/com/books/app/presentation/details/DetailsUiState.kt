package com.books.app.presentation.details

import androidx.compose.runtime.Stable
import com.books.app.data.model.Book
import kotlinx.collections.immutable.PersistentList

@Stable
data class DetailsUiState(
    val selectedBook: Book,
    val books: PersistentList<Book>,
    val recommendationBooks: PersistentList<Book>
)
