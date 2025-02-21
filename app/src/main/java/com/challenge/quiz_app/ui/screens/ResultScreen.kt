package com.challenge.quiz_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.challenge.quiz_app.ui.theme.customButtonColors
import com.challenge.quiz_app.viewmodel.QuizViewModel

@Composable
fun ThankYouScreen(viewModel: QuizViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = MaterialTheme.typography.titleLarge.toSpanStyle()
                ) {
                    append("Thank you")
                }
                withStyle(
                    style = MaterialTheme.typography.titleMedium.toSpanStyle()
                ) {
                    append("\nfor participating")
                }
            },
            Modifier.scale(2f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.restartQuiz() },
            modifier = Modifier
                .width(200.dp)
                .scale(1.25f)
                .padding(36.dp),
            colors = customButtonColors
        ) {
            Text("Start Over", textAlign = TextAlign.Center)
        }
    }
}