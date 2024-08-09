package com.books.app.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.books.app.presentation.ui.theme.BookAppTheme
import com.books.app.presentation.ui.theme.PinkLight

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {

    Button(
        onClick = onClick,
        modifier = modifier
            .height(48.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = PinkLight,
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displayMedium,
        )
    }

}


@Preview
@Composable
private fun PrimaryButtonPreview() {
    BookAppTheme {
        Box {
            PrimaryButton(
                text = "Read Now",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
