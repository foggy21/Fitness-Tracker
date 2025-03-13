package com.example.fitnesstracker.presentation.screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TextView : ViewModel() {
    var text by mutableStateOf("")
        private set

    fun updateText(text: String) {
        this.text = text
    }
}