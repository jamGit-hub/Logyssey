package com.example.logyssey.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.logyssey.R

val fontFam = FontFamily(
    Font(R.font.main_font, FontWeight.Medium),
    Font(R.font.click_ables, FontWeight.Light),
    Font(R.font.logyfont, FontWeight.Black) ,
            Font(R.font.sans, FontWeight.Bold)
)
// Set of Material typography styles to start with
val Typography = Typography(
     bodyLarge = TextStyle(
    fontFamily = fontFam,
    fontWeight = FontWeight.Medium,
    fontSize = 24.sp
),
    //  Body Text
    bodyMedium = TextStyle(
        fontFamily = fontFam,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontFam,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontFam,
        fontWeight = FontWeight.Light,
        fontSize = 10.sp
    ),
// clickables
    headlineSmall = TextStyle(
        fontFamily = fontFam,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fontFam,
        fontWeight = FontWeight.Light,
        fontSize = 24.sp
    ),

    headlineLarge = TextStyle(
        fontFamily = fontFam,
        fontWeight = FontWeight.Black,
        fontSize = 26.sp,

    ),
    labelMedium = TextStyle(
            fontFamily = fontFam,
    fontWeight = FontWeight.Bold,
    fontSize = 30.sp
)

)



