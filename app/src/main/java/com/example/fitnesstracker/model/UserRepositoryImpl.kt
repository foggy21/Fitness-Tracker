package com.example.fitnesstracker.model

class UserRepositoryImpl : UserRepository {
    private val users = mutableListOf<User>()

    override fun registerUser(user: User) {
        users.add(user)
    }

    override fun fetchUserByLogin(login: String): User? {
        return users.find {
            it.login == login
        }
    }
}