package com.example.fitnesstracker.model

interface UserRepository {
    fun registerUser(user: User)
    fun fetchUserByLogin(login: String) : User?
}