package com.example.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.R
import com.example.fitnesstracker.model.Gender
import com.example.fitnesstracker.model.User
import com.example.fitnesstracker.model.UserRepository
import com.example.fitnesstracker.presentation.state.AuthenticationEvent
import com.example.fitnesstracker.presentation.state.AuthenticationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthenticationUiState())
    val uiState: StateFlow<AuthenticationUiState> = _uiState

    private val _events = Channel<AuthenticationEvent>()
    val events = _events.receiveAsFlow()

    private val loginFlow = MutableSharedFlow<String>()
    private val passwordFlow = MutableSharedFlow<String>()
    private val repeatedPasswordFlow = MutableSharedFlow<String>()

    init {
        setupValidationFlow()
    }

    @OptIn(FlowPreview::class)
    private fun setupValidationFlow() {
        viewModelScope.launch {
            loginFlow
                .debounce(300)
                .collect{ login ->
                    isLoginUnique(login)
                }
        }

        viewModelScope.launch {
            passwordFlow
                .debounce(300)
                .collect{ password ->
                    validatePassword(password)
                }
        }

        viewModelScope.launch {
            repeatedPasswordFlow
                .debounce(300)
                .collect{ repeatedPassword ->
                    validateRepeatedPassword(repeatedPassword)
                }
        }
    }

    fun updateLogin(newLogin: String) {
        _uiState.update { currentComposer ->
            currentComposer.copy(
                login = newLogin,
                loginError = null
            )
        }
        viewModelScope.launch {
            loginFlow.emit(newLogin)
        }
    }

    fun updateNickname(newNickname: String) {
        _uiState.update { currentComposer ->
            currentComposer.copy(
                nickname = newNickname
            )
        }
    }

    fun updatePassword(newPassword: String) {
        _uiState.update { currentComposer ->
            currentComposer.copy(
                password = newPassword,
                passwordError = null
            )
        }
        viewModelScope.launch {
            passwordFlow.emit(newPassword)
        }
    }

    fun updateRepeatedPassword(newRepeatedPassword: String) {
        _uiState.update { currentComposer ->
            currentComposer.copy(
                repeatedPassword = newRepeatedPassword,
                repeatedPasswordError = null
            )
        }
        viewModelScope.launch {
            repeatedPasswordFlow.emit(newRepeatedPassword)
        }
    }

    fun updateGender(newGender: Gender) {
        _uiState.update { currentComposer ->
            currentComposer.copy(
                gender = newGender
            )
        }
    }

    fun togglePasswordVisibility() {
        _uiState.update { currentComposer ->
            currentComposer.copy(
                showPassword = !currentComposer.showPassword
            )
        }
    }

    fun toggleRepeatedPasswordVisibility() {
        _uiState.update { currentComposer ->
            currentComposer.copy(
                showRepeatedPassword = !currentComposer.showRepeatedPassword
            )
        }
    }

    private fun isLoginUnique(login: String): Boolean {
        return when {
            userRepository.fetchUserByLogin(login) != null -> {
                _uiState.update { currentComposer ->
                    currentComposer.copy(
                        loginError = "${R.string.error_login_exist}"
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
            password.length < 8 -> {
                _uiState.update { currentComposer ->
                    currentComposer.copy(
                        passwordError = "${R.string.error_password_length}"
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

    private fun validateRepeatedPassword(repeatedPassword: String): Boolean {
        return when {

            _uiState.value.password != repeatedPassword -> {
                _uiState.update { currentComposer ->
                    currentComposer.copy(
                        repeatedPasswordError = "${R.string.error_repeated_password_not_equal}"
                    )
                }
                false
            }
            else -> {
                _uiState.update { currentComposer ->
                    currentComposer.copy(
                        repeatedPasswordError = null
                    )
                }
                true
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val currentState = _uiState.value
                val loginUnique = isLoginUnique(currentState.login)
                val passwordValid = validatePassword(currentState.password)
                val repeatedPasswordValid = validateRepeatedPassword(currentState.repeatedPassword)

                if (loginUnique && passwordValid && repeatedPasswordValid) {
                    val newUser = User(
                        login = currentState.login,
                        nickname = currentState.nickname,
                        password = currentState.password,
                        gender = currentState.gender
                    )

                    delay(1000)
                    userRepository.registerUser(newUser)
                    _events.send(AuthenticationEvent.Success)
                }
            } catch (e: Exception) {
                _events.send(AuthenticationEvent.Error(e.message ?: "Неизвестная ошибка"))
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }

    }


}