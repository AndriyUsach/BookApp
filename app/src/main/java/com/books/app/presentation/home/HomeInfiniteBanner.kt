@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.books.app.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.books.app.data.model.BannerSlide
import com.books.app.presentation.ui.theme.ImagePlaceholderColor
import com.books.app.presentation.ui.theme.PinkDark
import com.books.app.presentation.util.PreviewData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val AUTOSCROLL_DELAY = 3_000L

@Composable
fun HomeInfiniteBanner(
    modifier: Modifier = Modifier,
    slides: List<BannerSlide>,
    onBookClicked: (bookId: Int) -> Unit = {}
) {
    val pageCount by remember { mutableIntStateOf(slides.size + 2) }

    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { pageCount }
    )

    if (pagerState.currentPage == 0 && pagerState.settledPage == 0) {
        scope.launch {
            if (pagerState.currentPageOffsetFraction != 0f)
                pagerState.animateScrollToPage(pagerState.currentPage)
            pagerState.scrollToPage(pageCount - 2)
        }
    }

    if (pagerState.currentPage == pageCount - 1 && pagerState.settledPage == pageCount - 1) {
        scope.launch {
            if (pagerState.currentPageOffsetFraction != 0f)
                pagerState.animateScrollToPage(pagerState.currentPage)
            pagerState.scrollToPage(1)
        }
    }

    Box(modifier = modifier) {

        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize(),
                pageSpacing = 16.dp,
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {

                val item = slides[pagerState.getRealCurrentPage(slides.size)]

                AsyncImage(
                    model = item.coverUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .background(ImagePlaceholderColor)
                        .clickable { onBookClicked(item.bookId) }
                )

            }

        }

        PagerIndicator(
            pageCount = slides.size,
            currentPage = pagerState.getRealCurrentPage(slides.size),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter)
        )

    }

    LaunchedEffect(key1 = pagerState.settledPage) {
        launch {
            delay(AUTOSCROLL_DELAY)
            with(pagerState) {
                animateScrollToPage(currentPage + 1)
            }
        }
    }

}

@Composable
private fun PagerIndicator(
    modifier: Modifier = Modifier,
    pageCount: Int,
    currentPage: Int
) {
    Row(modifier = modifier) {
        repeat(pageCount) { iteration ->
            val background = if (currentPage == iteration) {
                PinkDark
            } else {
                ImagePlaceholderColor
            }
            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(background)
            )

        }
    }
}

private fun PagerState.getRealCurrentPage(itemsCount: Int): Int {
    return when (currentPage) {
        0 -> itemsCount - 1
        pageCount - 1 -> 0
        else -> currentPage - 1
    }
}

@Preview
@Composable
private fun HomeInfiniteBannerPreview() {
    HomeInfiniteBanner(
        slides = PreviewData.getBannerSlideList(3),
        modifier = Modifier.height(150.dp)
    )
}