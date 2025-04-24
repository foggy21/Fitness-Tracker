package com.example.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.database.AuthRepository
import com.example.fitnesstracker.model.user.LoginUserDto
import com.example.fitnesstracker.presentation.state.AuthenticationEvent
import com.example.fitnesstracker.presentation.state.AuthenticationUiState
import com.example.fitnesstracker.res.AppStrings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    private val authRepository: AuthRepository
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
                        loginError = AppStrings.ERROR_LOGIN_BLANK
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
                        passwordError = AppStrings.ERROR_PASSWORD_BLANK
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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.update { it.copy(isLoading = true) }

                val currentState = _uiState.value
                if (!validateLogin(currentState.login)) {
                    _events.send(AuthenticationEvent.Error(AppStrings.ERROR_LOGIN_INVALID))
                    return@launch
                }
                if(!validatePassword(currentState.password)) {
                    _events.send(AuthenticationEvent.Error(AppStrings.ERROR_PASSWORD_INCORRECT))
                    return@launch
                }

                val login = authRepository.login(
                    LoginUserDto(
                        login = currentState.login,
                        password = currentState.password
                    )
                )
                delay(1000)
                if (login.isSuccess) {
                    _events.send(AuthenticationEvent.Success)
                    return@launch
                }
                _events.send(AuthenticationEvent.Error(AppStrings.ERROR_LOGIN_DATA_INVALID))
            } catch (e: Exception) {
                _events.send(AuthenticationEvent.Error(e.message ?: AppStrings.ERROR_UNKNOWN))
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}