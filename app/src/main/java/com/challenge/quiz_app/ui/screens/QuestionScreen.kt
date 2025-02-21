package com.challenge.quiz_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.challenge.quiz_app.model.Options
import com.challenge.quiz_app.model.Question
import com.challenge.quiz_app.model.Type
import com.challenge.quiz_app.ui.theme.customButtonColors
import com.challenge.quiz_app.viewmodel.QuizViewModel

@Composable
fun QuestionScreen(viewModel: QuizViewModel, question: Question, questionNum: Int) {
    val (selectedOptions, onOptionSelected) = remember { mutableStateOf<List<String>?>(null) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Question $questionNum",
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 48.sp,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.padding(12.dp))
        Text(
            question.questionText,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.padding(6.dp))
        when (question.options) {
            is Options.None -> {
                val onBackgroundColor = MaterialTheme.colorScheme.onBackground
                OutlinedTextField(
                    value = if (selectedOptions !== null) selectedOptions[0] else "",
                    onValueChange = { text -> onOptionSelected(listOf(text)) },
                    label = { Text("Your answer...") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = onBackgroundColor,
                        unfocusedBorderColor = onBackgroundColor,
                        focusedTextColor = onBackgroundColor,
                        unfocusedTextColor = onBackgroundColor,
                        unfocusedLabelColor = onBackgroundColor,
                        focusedLabelColor = onBackgroundColor,
                        cursorColor = onBackgroundColor
                    )
                )
            }

            is Options.ChoiceList -> {
                val configuration = LocalConfiguration.current
                val screenWidth = configuration.screenWidthDp.dp
                Column(
                    Modifier
                        .width(screenWidth - 15.dp)
                        .selectableGroup(),
                    verticalArrangement = Arrangement.spacedBy(
                        space = 6.dp,
                        alignment = Alignment.CenterVertically
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    question.options.option.forEach { option ->
                        Button(
                            onClick = {
                                println(option)
                                if (question.type == Type.Choice) onOptionSelected(listOf(option))
                                else {
                                    val isChecked = selectedOptions.orEmpty().contains(option)
                                    if (selectedOptions == null) onOptionSelected(listOf(option))
                                    else if (isChecked) onOptionSelected(
                                        selectedOptions.minus(
                                            option
                                        )
                                    )
                                    else onOptionSelected(selectedOptions.plus(option))
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp)),
                            colors = customButtonColors.copy(
                                containerColor =
                                if (selectedOptions !== null && selectedOptions.contains(option)) MaterialTheme.colorScheme.secondary
                                else Color.Transparent
                            )
                        ) {
                            Text(
                                option,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
        Spacer(Modifier.padding(12.dp))
        Button(
            enabled = selectedOptions != null,
            onClick = {
                viewModel.checkAnswer(selectedOptions!!)
            },
            modifier = Modifier.scale(1.25f),
            colors = customButtonColors
        ) {
            Text("Submit")
        }
    }
}
