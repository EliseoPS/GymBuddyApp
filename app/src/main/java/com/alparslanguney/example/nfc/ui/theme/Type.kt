package com.alparslanguney.example.nfc.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alparslanguney.example.nfc.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
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
val ubuntuFontFamily = FontFamily(
    Font(R.font.ubuntu_bold),
    Font(R.font.ubuntu_bolditalic, FontWeight.W300),
    Font(R.font.ubuntu_italic, FontWeight.W900),
    Font(R.font.ubuntu_light, FontWeight.W700),
    Font(R.font.ubuntu_lightitalic),
    Font(R.font.ubuntu_medium),
    Font(R.font.ubuntu_mediumitalic),
    Font(R.font.ubuntu_regular),
)

val ubuntuTypo = Typography(

    displayLarge = TextStyle(
        fontWeight = FontWeight.W300,
        fontFamily = ubuntuFontFamily,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.W300,
        fontFamily = ubuntuFontFamily,
        fontSize = 45.sp,
        lineHeight = 52.sp
    ),
    displaySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = ubuntuFontFamily,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = ubuntuFontFamily,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = ubuntuFontFamily,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = ubuntuFontFamily,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.W700,
        fontFamily = ubuntuFontFamily,
        fontSize = 40.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.W700,
        fontFamily = ubuntuFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.W700,
        fontFamily = ubuntuFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = ubuntuFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = ubuntuFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = ubuntuFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.W700,
        fontFamily = ubuntuFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.W700,
        fontFamily = ubuntuFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.W700,
        fontFamily = ubuntuFontFamily,
        fontSize = 11.sp,
        lineHeight = 16.sp
    )
)