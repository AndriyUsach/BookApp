@file:OptIn(ExperimentalFoundationApi::class)

package com.books.app.presentation.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.books.app.data.model.Book
import com.books.app.presentation.ui.theme.BookAppTheme
import com.books.app.presentation.ui.theme.ImagePlaceholderColor
import com.books.app.presentation.util.PreviewData
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsBookPager(
    modifier: Modifier = Modifier,
    books: List<Book>,
    onBookSelected: (bookId: Int) -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { books.size }
    )

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
    ) {

        val padding = with(density) {
            val pxPadding = (maxWidth.toPx() * 0.5f).roundToInt() / 2
            pxPadding.toDp()
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
            ,
            contentPadding = PaddingValues(horizontal = padding),
            pageSpacing = 16.dp
        ) {page ->
            val item = books[page]

            HeaderBookItem(
                page = page,
                book = item,
                pagerState = pagerState
            ) {
                scope.launch {
                    pagerState.animateScrollToPage(page)
                }
            }
        }

    }

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            books.getOrNull(page)
                ?.let { onBookSelected(it.id) }
        }
    }


}


private fun PagerState.pageOffset(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}


@Composable
private fun HeaderBookItem(
    modifier: Modifier = Modifier,
    page: Int,
    book: Book,
    pagerState: PagerState,
    onBookClicked: () -> Unit = {}
) {

    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = book.coverUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    val pagerOffset = pagerState.pageOffset(page).absoluteValue

                    val scale = lerp(1f, 0.8f, pagerOffset)
                    scaleX = scale
                    scaleY = scale
                }
                .aspectRatio(0.8f)
                .clip(RoundedCornerShape(16.dp))
                .background(ImagePlaceholderColor)
                .clickable { onBookClicked() }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    val pagerOffset = pagerState.pageOffset(page).absoluteValue

                    val alpha = lerp(1f, 0f, pagerOffset)
                    this.alpha = alpha
                }
        ) {
            Text(
                text = book.name,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White.copy(),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            Text(
                text = book.author,
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
        }


    }

}

@Preview
@Composable
private fun DetailsBookPagerPreview() {
    BookAppTheme {
        DetailsBookPager(books = PreviewData.getBookList(5))
    }
}