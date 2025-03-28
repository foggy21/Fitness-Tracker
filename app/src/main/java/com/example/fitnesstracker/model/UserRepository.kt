package com.example.fitnesstracker.model

class UserRepository {
    private val users = mutableListOf<User>()

    fun registerUser(user: User) {
        users.add(user);
    }

    fun fetchUserByLogin(login: String): User? {
        return users.find {
            it.login == login
        }
    }
}