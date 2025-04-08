package com.example.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.R
import com.example.fitnesstracker.model.UserRepository
import com.example.fitnesstracker.presentation.state.AuthenticationEvent
import com.example.fitnesstracker.presentation.state.AuthenticationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthenticationUiState())
    val uiState: StateFlow<AuthenticationUiState> = _uiState

    private val _events = Channel<AuthenticationEvent>()
    val events = _events.receiveAsFlow()

    fun updateLogin(newLogin: String) {
        _uiState.update { currentComposer ->
            currentComposer.copy(
                login = newLogin
            )
        }
    }

    fun updatePassword(newPassword: String) {
        _uiState.update { currentComposer ->
            currentComposer.copy(
                password = newPassword
            )
        }
    }

    private fun validateLogin(login: String): Boolean {
        return when {
            login.isBlank() -> {
                _uiState.update { currentComposer ->
                    currentComposer.copy(
                        loginError = "${R.string.error_login_blank}"
                    )
                }
                false
            }
            else -> {
                _uiState.update { currentComposer ->
                    currentComposer.copy(
                        loginError = null
                    )
                }
                true
            }
        }
    }

    private fun validatePassword(password: String): Boolean {
        return when {
            password.isBlank() -> {
                _uiState.update { currentComposer ->
                    currentComposer.copy(
                        passwordError = "${R.string.error_password_blank}"
                    )
                }
                false
            }
            else -> {
                _uiState.update { currentComposer ->
                    currentComposer.copy(
                        passwordError = null
                    )
                }
                true
            }
        }
    }

    fun togglePasswordVisibility() {
        _uiState.update { currentComposer ->
            currentComposer.copy(
                showPassword = !currentComposer.showPassword
            )
        }
    }

    fun login() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val currentState = _uiState.value
                val loginValid = validateLogin(currentState.login)
                val passwordValid = validatePassword(currentState.password)

                if (loginValid && passwordValid) {
                    val user = userRepository.fetchUserByLogin(currentState.login)
                    if (user != null) {
                        if (user.password == currentState.password){
                            delay(1000)
                            _events.send(AuthenticationEvent.Success)
                        }
                        throw Exception("${R.string.error_password_incorrect}")
                    }
                    throw Exception("${R.string.error_login_not_exist}")
                }
            } catch (e: Exception) {
                _events.send(AuthenticationEvent.Error(e.message ?: "Неизвестная ошибка"))
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}