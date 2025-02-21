package com.challenge.quiz_app.model

sealed class Options {
    data object None: Options()
    data class ChoiceList(val option: List<String>): Options()
}

sealed class Answer {
    data class One(val answer: String): Answer()
    data class Multiple(val answers: List<String>): Answer()
}

enum class Type { Choice, MultipleChoice, Open }

data class Question(
    val questionText: String,
    val type: Type,
    val options: Options,
    val correctAnswer: Answer
)