package com.example.fitnesstracker.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fitnesstracker.model.User
import com.example.fitnesstracker.model.UserRepository

class RegisterViewModel : ViewModel() {
    private val userRepository : UserRepository = UserRepository()

    private val _login = mutableStateOf("")
    val login: State<String> = _login
    private val _nickname = mutableStateOf("")
    val nickname: State<String> = _nickname
    private val _password = mutableStateOf("")
    val password: State<String> = _password
    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword
    private val _repeatedPassword = mutableStateOf("")
    val repeatedPassword: State<String> = _repeatedPassword
    private val _showRepeatedPassword = mutableStateOf(false)
    val showRepeatedPassword: State<Boolean> = _showRepeatedPassword

    private val _loginError = mutableStateOf<String?>(null)
    val loginError: State<String?> = _loginError
    private val _passwordError = mutableStateOf<String?>(null)
    val passwordError: State<String?> = _passwordError
    private val _repeatedPasswordError = mutableStateOf<String?>(null)
    val repeatedPasswordError: State<String?> = _repeatedPasswordError

    fun updateLogin(newLogin: String) {
        _login.value = newLogin
        validateLogin(newLogin)
    }

    fun updateNickname(newNickname: String) {
        _nickname.value = newNickname
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
        validatePassword(newPassword)
    }

    fun updateRepeatedPassword(newRepeatedPassword: String) {
        _repeatedPassword.value = newRepeatedPassword
        validateRepeatedPassword(newRepeatedPassword)
    }

    fun togglePasswordVisibility() {
        _showPassword.value = !_showPassword.value
    }

    fun toggleRepeatedPasswordVisibility() {
        _showRepeatedPassword.value = !_showRepeatedPassword.value
    }

    private fun validateLogin(login: String): Boolean {
        return when {
            userRepository.fetchUserByLogin(login) != null -> {
                _loginError.value = "Пользователь с таким логином уже существует"
                false
            }
            else -> {
                _loginError.value = null
                true
            }
        }
    }

    private fun validatePassword(password: String): Boolean {
        return when {
            password.length < 8 -> {
                _passwordError.value = "Пароль должен содержать минимум 8 символов"
                false
            }
            else -> {
                _passwordError.value = null
                true
            }
        }
    }

    private fun validateRepeatedPassword(repeatedPassword: String): Boolean {
        return when {
            _password.value != repeatedPassword -> {
                _repeatedPasswordError.value = "Пароли должны совпадать"
                false
            }
            else -> {
                _repeatedPasswordError.value = null
                true
            }
        }
    }

    fun register() {
        val loginValid = validateLogin(_login.value)
        val passwordValid = validatePassword(_password.value)
        val repeatedPasswordValid = validateRepeatedPassword(_repeatedPassword.value)

        if (loginValid && passwordValid && repeatedPasswordValid) {
            val newUser = User(
                login = _login.value,
                nickname = _nickname.value,
                password =  _password.value
            )

            userRepository.registerUser(newUser)
        }
    }


}