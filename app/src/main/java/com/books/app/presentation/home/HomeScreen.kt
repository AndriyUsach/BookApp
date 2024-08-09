package com.books.app.presentation.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.books.app.data.model.BannerSlide
import com.books.app.data.model.Book
import com.books.app.presentation.components.BookListWithTitle
import com.books.app.presentation.ui.theme.BookAppTheme
import com.books.app.presentation.ui.theme.HomeBG
import com.books.app.presentation.util.PreviewData
import kotlinx.collections.immutable.persistentListOf


@Composable
fun HomeScreenRoute(
    viewModel: HomeVM = hiltViewModel(),
    onBookClicked: (bookId: Int) -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(uiState = uiState, onBookClicked = onBookClicked)

}

@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    onBookClicked: (bookId: Int) -> Unit = {}
) {

    val listState = rememberLazyListState()

    Scaffold(
        containerColor = HomeBG,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeAppBar()
        },
        content = { innerPadding ->

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                item {
                    HomeInfiniteBanner(
                        slides = uiState.topBannerSlides,
                        onBookClicked = onBookClicked,
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 40.dp)
                            .fillMaxWidth()
                            .height(160.dp)
                    )
                }

                items(uiState.genres, key = { it.genre }) { genreWithBooks ->

                    CompositionLocalProvider(LocalContentColor provides Color.White.copy(alpha = 0.7f)) {
                        BookListWithTitle(
                            books = genreWithBooks.books,
                            title = genreWithBooks.genre,
                            titleColor = Color.White,
                            onBookClicked = onBookClicked,
                            modifier = Modifier
                                .padding(bottom = 24.dp)
                        )
                    }

                }

            }

        }
    )
}


@Preview
@Composable
private fun HomeScreenPreview() {
    BookAppTheme {

        val banners = PreviewData.getBannerSlideList(3)
        val genres = List(3) {
            val books = PreviewData.getBookList(10)
            GenreWithBooks(
                genre = "Genre$it",
                books = persistentListOf<Book>().addAll(books)
            )
        }

        val state = HomeUiState(
            topBannerSlides = persistentListOf<BannerSlide>().addAll(banners),
            genres = persistentListOf<GenreWithBooks>().addAll(genres)
        )

        HomeScreen(state) {}
    }
}