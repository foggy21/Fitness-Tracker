package com.example.fitnesstracker.model.user

data class User(
    var login: String,
    var nickname: String,
    var password: String,
    var gender: Gender
)