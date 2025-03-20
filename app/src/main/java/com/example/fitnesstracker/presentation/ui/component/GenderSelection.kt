package com.example.fitnesstracker.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.R

enum class Gender(val gender: Int) {
    Male(R.string.male),
    Female(R.string.female),
    Another(R.string.another_gender)
}

@Composable
fun GenderSelection(
    selectedGender: Gender,
    modifier: Modifier = Modifier
) {
    val radioButtons = mutableListOf<String>()
    enumValues<Gender>().forEach { value ->
        radioButtons.add(stringResource(id = value.gender))
    }
    var (selectedButton, onButtonSelect) = remember { mutableStateOf(selectedGender.name) }
    radioButtons.forEach{
        val isSelected = it == selectedButton
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(36.dp)
                .selectable(
                    selected = isSelected,
                    onClick = { onButtonSelect(it) },
                    role = Role.RadioButton
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                modifier = modifier,
                selected = isSelected,
                onClick = { selectedButton = it }
            )
            Text(
                text = it,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


