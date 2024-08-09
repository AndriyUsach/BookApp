package com.books.app.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.books.app.R
import com.books.app.presentation.ui.theme.BookAppTheme
import com.books.app.presentation.ui.theme.PinkLight
import kotlinx.coroutines.delay


private const val SPLASH_SCREEN_DELAY = 2_000L

@Composable
fun SplashScreen(
    navToMainScreen: () -> Unit = {}
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.image_splash_background),
                contentScale = ContentScale.Crop
            )
    ) {

        Image(
            painter = painterResource(id = R.drawable.image_heart),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .padding(bottom = 81.dp)
        ) {

            Text(
                text = stringResource(id = R.string.splash_title),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = PinkLight,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(id = R.string.splash_subtitle),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            )

            LinearProgressIndicator(
                strokeCap = StrokeCap.Round,
                color = Color.White,
                trackColor = Color.White.copy(alpha = 0.2f),
                modifier = Modifier
                    .padding(top = 19.dp)
                    .fillMaxWidth()
                    .height(6.dp)
                    .padding(horizontal = 50.dp)
            )

        }

    }

    LaunchedEffect(key1 = null) {
        delay(SPLASH_SCREEN_DELAY)
        navToMainScreen()
    }

}

@Preview
@Composable
private fun SplashScreenPreview() {
    BookAppTheme {
        SplashScreen()
    }
}