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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.model.Gender
import com.example.fitnesstracker.viewmodel.RegisterViewModel


@Composable
fun GenderSelection(
    viewModel: RegisterViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val radioButtons = mutableMapOf<Int, Gender>()
    enumValues<Gender>().forEach { gender ->
        radioButtons[gender.gender] = gender
    }
    radioButtons.forEach{
        val isSelected = it.value.name == uiState.gender.name
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(36.dp)
                .selectable(
                    selected = isSelected,
                    onClick = { viewModel.updateGender(it.value) },
                    role = Role.RadioButton
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                modifier = modifier,
                selected = isSelected,
                onClick = { viewModel.updateGender(it.value) }
            )
            Text(
                text = stringResource(id = it.key),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


