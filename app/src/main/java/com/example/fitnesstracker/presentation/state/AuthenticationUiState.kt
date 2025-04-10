package com.example.fitnesstracker.presentation.state

import com.example.fitnesstracker.model.user.Gender

data class AuthenticationUiState(
    val login: String = "",
    val nickname: String = "",
    val password: String = "",
    val showPassword: Boolean = false,
    val repeatedPassword: String = "",
    val showRepeatedPassword: Boolean = false,
    val gender: Gender = Gender.Another,
    val isLoading: Boolean = false,
    val loginError: String? = null,
    val passwordError: String? = null,
    val repeatedPasswordError: String? = null,
)
