package com.books.app.presentation.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.books.app.R
import com.books.app.data.model.Book
import com.books.app.presentation.components.BookListWithTitle
import com.books.app.presentation.components.PrimaryButton
import com.books.app.presentation.ui.theme.BookAppTheme
import com.books.app.presentation.ui.theme.SecondTextColor
import com.books.app.presentation.ui.theme.TextColor
import com.books.app.presentation.util.PreviewData
import kotlinx.collections.immutable.persistentListOf

@Composable
fun DetailsScreenRoute(
    bookId: Int?,
    onBookClicked: (bookId: Int) -> Unit = {},
    navUp: () -> Unit = {}
) {

    val viewModel = hiltViewModel<DetailsVM, DetailsVM.DetailsVMFactory> { factory ->
        factory.create(bookId)
    }

    val uiState by viewModel.uiState.collectAsState()

    uiState?.let { state ->
        DetailsScreen(
            uiState = state,
            onBookSelected = { bookId ->
                viewModel.selectBookById(bookId)
            },
            onBookClicked = onBookClicked,
            navUp = navUp
        )
    }
}

@Composable
private fun DetailsScreen(
    uiState: DetailsUiState,
    onBookSelected: (bookId: Int) -> Unit = {},
    onBookClicked: (bookId: Int) -> Unit = {},
    navUp: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {

        DetailsHeaderBackground(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.8f)
        )

        Column(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
        ) {

            Header(
                books = uiState.books,
                onBookSelected = onBookSelected,
                navUp = navUp
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                contentPadding = PaddingValues(vertical = 20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(Color.White)
            ) {

                // Book Details
                item {
                    AnimatedContent(
                        targetState = uiState.selectedBook,
                        label = "BookDetailsInfoAnim",
                        transitionSpec = {
                            fadeIn(animationSpec = tween(320, delayMillis = 90))
                                .togetherWith(fadeOut(animationSpec = tween(200)))
                        }
                    ) { selectedBook ->
                        BookDetailsInfo(
                            selectedBook,
                            modifier = Modifier.padding(
                                bottom = 16.dp,
                                start = 16.dp,
                                end = 16.dp
                            )
                        )
                    }
                }

                // Recommendation block
                item {
                    CompositionLocalProvider(LocalContentColor provides SecondTextColor) {
                        BookListWithTitle(
                            books = uiState.recommendationBooks,
                            title = stringResource(id = R.string.recommendation_title),
                            titleColor = TextColor,
                            onBookClicked = onBookClicked
                        )
                    }
                }

                // Read Now button
                item {
                    PrimaryButton(
                        text = stringResource(id = R.string.read_now),
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                            .fillMaxWidth()
                    ) { }
                }

            }

        }
        
    }
}

@Composable
private fun ColumnScope.Header(
    books: List<Book>,
    onBookSelected: (bookId: Int) -> Unit = {},
    navUp: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { navUp() }
        )
    }

    DetailsBookPager(
        books = books,
        onBookSelected = onBookSelected,
        modifier = Modifier
            .padding(top = 4.dp)
    )
}

@Preview(device = "spec:width=1080px,height=3340px,dpi=440")
@Composable
private fun DetailsScreenPreview() {
    BookAppTheme {

        val books = PreviewData.getBookList(5)

        val state = DetailsUiState(
            selectedBook = books.first(),
            books = persistentListOf(*books.toTypedArray()),
            recommendationBooks = persistentListOf(*books.toTypedArray())
        )

        DetailsScreen(uiState = state)
    }
}