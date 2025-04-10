package com.example.fitnesstracker.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.model.user.Gender
import com.example.fitnesstracker.viewmodel.RegisterViewModel


@Composable
fun GenderSelection(
    viewModel: RegisterViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val radioButtons = mutableListOf<Gender>()
    enumValues<Gender>().forEach { gender ->
        radioButtons.add(gender)
    }
    radioButtons.forEach{
        val isSelected = it.gender == uiState.gender.gender
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(36.dp)
                .selectable(
                    selected = isSelected,
                    onClick = { viewModel.updateGender(it) },
                    role = Role.RadioButton
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                modifier = modifier,
                selected = isSelected,
                onClick = { viewModel.updateGender(it) }
            )
            Text(
                text = it.gender,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


