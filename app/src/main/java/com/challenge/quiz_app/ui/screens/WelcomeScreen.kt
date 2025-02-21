package com.challenge.quiz_app.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.challenge.quiz_app.ui.theme.customButtonColors
import com.challenge.quiz_app.viewmodel.QuizViewModel

@Composable
fun WelcomeScreen(viewModel: QuizViewModel) {
    val scale = remember { Animatable(1f) }

    LaunchedEffect(key1 = Unit) {
        scale.animateTo(
            targetValue = 2f,
            animationSpec = tween(durationMillis = 1250, easing = LinearOutSlowInEasing)
        )
    }

    Text(
        text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.titleMedium.toSpanStyle()
            ) {
                append("Welcome to ")
            }
            withStyle(
                style = MaterialTheme.typography.titleLarge.toSpanStyle()
            ) {
                append("Quizcut")
            }
        },
        modifier = Modifier
            .graphicsLayer {
                scaleX = scale.value
                scaleY = scale.value
                transformOrigin = TransformOrigin.Center
            },
        style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated),
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = { viewModel.nextQuestion() },
        modifier = Modifier
            .width(200.dp)
            .scale(1.25f)
            .padding(36.dp),
        colors = customButtonColors
    ) {
        Text("Start")
    }
}