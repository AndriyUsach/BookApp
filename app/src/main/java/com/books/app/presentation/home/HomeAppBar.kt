@file:OptIn(ExperimentalMaterial3Api::class)

package com.books.app.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.books.app.R
import com.books.app.presentation.ui.theme.BookAppTheme
import com.books.app.presentation.ui.theme.PinkDark

@Composable
fun HomeAppBar() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        Text(
            text = stringResource(id = R.string.title_home),
            style = MaterialTheme.typography.titleSmall,
            color = PinkDark,
            modifier = Modifier
                .height(48.dp)
                .padding(start = 16.dp, top = 18.dp)
        )
    }
}

@Preview
@Composable
private fun HomeAppBarPreview() {
    BookAppTheme {
        HomeAppBar()
    }
}