package com.example.fitnesstracker.presentation.state

data class RegisterUiState(
    val login: String = "",
    val nickname: String = "",
    val password: String = "",
    val showPassword: Boolean = false,
    val repeatedPassword: String = "",
    val showRepeatedPassword: Boolean = false,
    val isLoading: Boolean = false,
    val loginError: String? = null,
    val passwordError: String? = null,
    val repeatedPasswordError: String? = null,
)
