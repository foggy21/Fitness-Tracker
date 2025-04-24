package com.example.fitnesstracker.model.user

data class RegisterUserDto(
    val login: String,
    val username: String,
    val password: String,
    val gender: Gender
)
