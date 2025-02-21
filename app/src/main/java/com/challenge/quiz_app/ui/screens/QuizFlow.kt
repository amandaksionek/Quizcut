package com.challenge.quiz_app.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.challenge.quiz_app.viewmodel.QuizViewModel
import kotlinx.coroutines.delay

@Composable
fun Quiz(viewModel: QuizViewModel) {
    val hasQuizStarted by viewModel.hasQuizStarted
    val isQuizCompleted by viewModel.isQuizCompleted
    val isAnswerCorrect by viewModel.isAnswerCorrect
    val currentQuestion by viewModel.currentQuestion

    if (isQuizCompleted) {
        ThankYouScreen(viewModel)
    } else {
        if (!hasQuizStarted) {
            WelcomeScreen(viewModel)
        } else {
            if (isAnswerCorrect !== null) {
                LaunchedEffect(isAnswerCorrect) {
                    delay(1500)
                    viewModel.nextQuestion()
                }
                AnswerScreen(isAnswerCorrect!!)
            } else {
                val num = viewModel.getQuestionNumber()
                QuestionScreen(viewModel, currentQuestion, num)
            }
        }
    }
}