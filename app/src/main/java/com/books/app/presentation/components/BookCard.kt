package com.books.app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.books.app.data.model.Book
import com.books.app.presentation.ui.theme.BookAppTheme
import com.books.app.presentation.ui.theme.ImagePlaceholderColor
import com.books.app.presentation.util.PreviewData

@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    book: Book,
    onBookClicked: () -> Unit = {},
) {

    Column(
        modifier = modifier then Modifier
            .width(120.dp)
    ) {
        AsyncImage(
            model = book.coverUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(ImagePlaceholderColor)
                .clickable { onBookClicked() }
        )

        Text(
            text = book.name,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 2,
            minLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
        )
    }

}

@Preview
@Composable
private fun BookCardPreview() {
    BookAppTheme {
        BookCard(book = PreviewData.getBook())
    }
}