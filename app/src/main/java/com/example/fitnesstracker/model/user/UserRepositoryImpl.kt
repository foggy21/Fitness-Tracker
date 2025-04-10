package com.example.fitnesstracker.model.user

class UserRepositoryImpl : UserRepository {
    private val users = mutableMapOf<String, User>()

    override fun registerUser(user: User) {
        users[user.login] = user
    }

    override fun fetchUserByLogin(login: String): User? {
        return users[login]
    }
}