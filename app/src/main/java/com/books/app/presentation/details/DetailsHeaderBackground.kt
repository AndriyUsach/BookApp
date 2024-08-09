package com.books.app.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.books.app.R
import com.books.app.presentation.ui.theme.BookAppTheme

private val backgroundColor = Color(0xFF532454)

private val gradientColors = listOf(
    Color(0x00532454),
    Color(0xD9532454),
    Color.Black.copy(alpha = 0.98f),
    Color.Black.copy(alpha = 0f)
)

@Composable
fun DetailsHeaderBackground(modifier: Modifier = Modifier) {

    Image(
        painter = painterResource(id = R.drawable.image_details_bg),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .drawWithCache {
                val brush = Brush.verticalGradient(
                    gradientColors,
                    startY = -916f,
                    endY = 419f
                )

                onDrawWithContent {
                    drawRect(backgroundColor)
                    drawRect(brush)
                    drawContent()
                    drawRect(Color.Black.copy(alpha = 0.7f))
                }
            }
            .alpha(0.4f)
    )
}


@Preview
@Composable
private fun DetailsHeaderBackgroundPreview() {
    BookAppTheme {
        Box {
            DetailsHeaderBackground(
                modifier = Modifier.fillMaxWidth()
                    .aspectRatio(0.9f)
            )
        }
    }
}