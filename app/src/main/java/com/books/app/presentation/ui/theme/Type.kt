package com.books.app.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.books.app.R

// Set of Material typography styles to start with

val GeorgiaFontFamily = FontFamily(
    Font(R.font.georgia_italic, FontWeight.Normal, FontStyle.Italic)
)

val NunitoSansFontFamily = FontFamily(
    Font(R.font.nunito_sans, FontWeight.Normal)
)

val Typography = Typography(

    titleLarge = TextStyle(
        fontFamily = GeorgiaFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 52.sp,
        lineHeight = 52.sp
    ),

    titleMedium = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 24.sp,
        lineHeight = 26.4.sp
    ),

    titleSmall = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 20.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.41).sp
    ),

    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 20.sp,
        lineHeight = 22.sp,
    ),

    bodySmall = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 12.sp,
        lineHeight = 13.2.sp,
        letterSpacing = (-0.41).sp
    ),

    labelLarge = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.41).sp
    ),

    labelMedium = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        lineHeight = 17.6.sp,
        letterSpacing = (-0.41).sp
    ),
    labelSmall = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 14.sp,
        lineHeight = 15.4.sp,
        letterSpacing = (-0.41).sp
    ),
    displayMedium = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.W800,
        fontSize = 16.sp,
        lineHeight = 16.sp,
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)