package com.books.app.presentation.home

import androidx.compose.runtime.Stable
import com.books.app.data.model.BannerSlide
import com.books.app.data.model.Book
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class HomeUiState(
    val topBannerSlides: PersistentList<BannerSlide> = persistentListOf(),
    val genres: PersistentList<GenreWithBooks> = persistentListOf()
)

@Stable
data class GenreWithBooks(
    val genre: String,
    val books: ImmutableList<Book>
)


