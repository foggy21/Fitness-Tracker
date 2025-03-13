package com.example.fitnesstracker.presentation.screen.viewmodel

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PasswordView : ViewModel() {
    val password = TextFieldState()
    var showPassword by mutableStateOf(false)


}


