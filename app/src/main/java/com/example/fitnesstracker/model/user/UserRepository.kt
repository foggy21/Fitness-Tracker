package com.example.fitnesstracker.model.user

interface UserRepository {
    fun registerUser(user: User)
    fun fetchUserByLogin(login: String) : User?
}