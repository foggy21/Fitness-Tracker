package com.example.fitnesstracker.data.database

import com.example.fitnesstracker.domain.dao.UserDao
import com.example.fitnesstracker.domain.entity.User
import com.example.fitnesstracker.res.AppStrings
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun fetchUserByUsername(username: String): Result<User?> {
        val user = userDao.getUserByUsername(username)
        if (user != null) {
            return Result.success(user)
        }
        return Result.failure(IllegalArgumentException(AppStrings.ERROR_USERNAME_INVALID))
    }

    suspend fun fetchUserByLogin(login: String): Result<User?> {
        val user = userDao.getUserByLogin(login)
        if (user != null) {
            return Result.success(user)
        }
        return Result.failure(IllegalArgumentException(AppStrings.ERROR_LOGIN_INVALID))
    }
}