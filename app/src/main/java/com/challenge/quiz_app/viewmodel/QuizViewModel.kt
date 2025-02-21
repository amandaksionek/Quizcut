package com.challenge.quiz_app.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.challenge.quiz_app.model.Answer
import com.challenge.quiz_app.model.Options
import com.challenge.quiz_app.model.Question
import com.challenge.quiz_app.model.Type


class QuizViewModel : ViewModel() {

    private val _hasQuizStarted = mutableStateOf(false)
    val hasQuizStarted: State<Boolean> = _hasQuizStarted

    private val _isAnswerCorrect = mutableStateOf<Boolean?>(null)
    val isAnswerCorrect: State<Boolean?> = _isAnswerCorrect

    private val _isQuizCompleted = mutableStateOf(false)
    val isQuizCompleted: State<Boolean> = _isQuizCompleted

    private val questions = listOf(
        Question(
            "True or False: The onCreate() method is called every time an activity is resumed.",
            Type.Choice,
            Options.ChoiceList(listOf("TRUE", "FALSE")),
            Answer.One("FALSE")
        ),
        Question(
            "Which of the following functions is used to create a UI element that listens for user clicks?",
            Type.Choice,
            Options.ChoiceList(listOf("Button()", "Clickable()", "ClickHandler()", "OnClick()")),
            Answer.One("Button()")
        ),
        Question(
            "Which of the following are valid Jetpack Compose layouts? (Select all that apply)",
            Type.MultipleChoice,
            Options.ChoiceList(listOf("Column()", "Row()", "Grid()", "Box()")),
            Answer.Multiple(listOf("Column()", "Row()", "Box()"))
        ),
        Question(
            "Which Jetpack Compose function is used to define the main entry point of a UI in a Compose-based app?",
            Type.Open,
            Options.None,
            Answer.One("setContent")
        )
    )

    private val _currentQuestion = mutableStateOf(questions[0])
    val currentQuestion: State<Question> = _currentQuestion

    fun getQuestionNumber(): Int {
        return questions.indexOf(_currentQuestion.value) + 1
    }

    fun checkAnswer(answer: List<String>) {
        val correctAnswer = _currentQuestion.value.correctAnswer
        val castedAnswer: Answer = if (answer.size == 1) Answer.One(answer[0])
        else Answer.Multiple(answer)
        _isAnswerCorrect.value = castedAnswer == correctAnswer
    }

    fun nextQuestion() {
        val index = questions.indexOf(_currentQuestion.value)
        if (index == questions.size - 1)
            _isQuizCompleted.value = true
        else {
            if (!_hasQuizStarted.value)
                _hasQuizStarted.value = true
            else
                _currentQuestion.value = questions[index + 1]
            _isAnswerCorrect.value = null
        }
    }

    fun restartQuiz() {
        _hasQuizStarted.value = false
        _isAnswerCorrect.value = null
        _isQuizCompleted.value = false
        _currentQuestion.value = questions[0]
    }
}
