package com.example.fitnesstracker.presentation.screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PasswordView : ViewModel() {
    var password by mutableStateOf("")
        private set

    var showPassword by mutableStateOf(false)

    fun updatePassword(password: String) {
        this.password = password
    }
}


