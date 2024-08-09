package com.books.app.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.books.app.R
import com.books.app.data.model.Book
import com.books.app.presentation.ui.theme.BookAppTheme
import com.books.app.presentation.ui.theme.DividerColor
import com.books.app.presentation.ui.theme.LabelColor
import com.books.app.presentation.ui.theme.SecondTextColor
import com.books.app.presentation.ui.theme.TextColor
import com.books.app.presentation.util.PreviewData

@Composable
fun BookDetailsInfo(
    book: Book,
    modifier: Modifier = Modifier
) {

    val statsList = listOf(
        R.string.label_readers to book.views,
        R.string.label_likes to book.likes,
        R.string.label_quotes to book.quotes,
        R.string.label_genre to book.genre
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            statsList.forEach { (stringResId, stats) ->
                BookStatsBadge(
                    stats = stats,
                    statsName = stringResource(id = stringResId),
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        HorizontalDivider(thickness = 1.dp, color = DividerColor)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Summary",
            color = TextColor,
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = book.summary,
            color = SecondTextColor,

            )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(thickness = 1.dp, color = DividerColor)

    }
}

@Composable
private fun BookStatsBadge(
    stats: String,
    statsName: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = stats,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center,
            color = TextColor,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = statsName,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = LabelColor,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun BookDetailsInfoPreview() {
    BookAppTheme {
        BookDetailsInfo(
            book = PreviewData.getBook(),
            modifier = Modifier.background(Color.White)
        )
    }
}