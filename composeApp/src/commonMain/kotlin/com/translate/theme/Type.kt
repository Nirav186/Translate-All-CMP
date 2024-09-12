package com.translate.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import translate.composeapp.generated.resources.Res
import translate.composeapp.generated.resources.montserratbold
import translate.composeapp.generated.resources.montserratregular
import translate.composeapp.generated.resources.montserratsemibold
import translate.composeapp.generated.resources.roboto_medium_numbers

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

//val montRFont = Res.font.montserratregular
//val montBFont = Res.font.montserratbold
//val montSBFont = Res.font.montserratsemibold
//val robotoFont = Res.font.roboto_medium_numbers
//
//val montFamily = FontFamily(
//    listOf(montBFont, montRFont, montSBFont, robotoFont)
//)
