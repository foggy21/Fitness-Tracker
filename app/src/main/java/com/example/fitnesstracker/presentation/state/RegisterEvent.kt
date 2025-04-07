package com.example.fitnesstracker.presentation.state

sealed class RegisterEvent {
    data object Success : RegisterEvent()
    data class Error(val message: String) : RegisterEvent()
}