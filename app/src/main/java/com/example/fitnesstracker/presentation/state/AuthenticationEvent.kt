package com.example.fitnesstracker.presentation.state

sealed class AuthenticationEvent {
    data object Success : AuthenticationEvent()
    data class Error(val message: String) : AuthenticationEvent()
}