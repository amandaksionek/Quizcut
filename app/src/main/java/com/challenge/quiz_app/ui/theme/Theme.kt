package com.challenge.quiz_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = LapisLazuli,
    secondary = Verdigris,
    tertiary = Emerald,
    background = PrussianBlue,
    surface = PrussianBlue,
    onPrimary = TeaGreen,
    onSecondary = TeaGreen,
    onTertiary = Verdigris,
    onBackground = TeaGreen,
    onSurface = AshGray
)

private val LightColorScheme = lightColorScheme(
    primary = TeaGreen,
    secondary = LightGreen,
    tertiary = Emerald,
    background = Verdigris,
    surface = PrussianBlue,
    onPrimary = LapisLazuli,
    onSecondary = LapisLazuli,
    onTertiary = LapisLazuli,
    onBackground = PrussianBlue,
    onSurface = AshGray
)

var customButtonColors = ButtonColors(
    containerColor = LightColorScheme.primary,
    contentColor = LightColorScheme.primary,
    disabledContainerColor = Color.Gray,
    disabledContentColor = Color.LightGray
)

@Composable
fun QuizAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    customButtonColors = ButtonDefaults.buttonColors(
        containerColor = colorScheme.primary,
        contentColor = colorScheme.onPrimary,
        disabledContainerColor = Color.Gray,
        disabledContentColor = Color.LightGray
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = quizAppTypography(),
        content = content
    )
}