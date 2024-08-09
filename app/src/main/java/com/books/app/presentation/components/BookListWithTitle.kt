package com.books.app.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.books.app.data.model.Book
import com.books.app.presentation.ui.theme.BookAppTheme
import com.books.app.presentation.util.PreviewData

@Composable
fun BookListWithTitle(
    modifier: Modifier = Modifier,
    books: List<Book>,
    title: String,
    titleColor: Color,
    onBookClicked: (bookId: Int) -> Unit = {},
) {

    val scrollState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            color = titleColor,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            state = scrollState,
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {

            items(books, key = { it.id }) { book ->
                BookCard(
                    book = book,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    onBookClicked = {
                        onBookClicked(book.id)
                    }
                )
            }
        }
    }

}


@Preview
@Composable
private fun BookListWithTitlePreview() {
    BookAppTheme {
        BookListWithTitle(
            books = PreviewData.getBookList(10),
            title = "You will also like",
            titleColor = Color.White
        )
    }
}