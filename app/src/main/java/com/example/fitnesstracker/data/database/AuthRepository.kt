package com.example.fitnesstracker.data.database

import com.example.fitnesstracker.domain.dao.UserDao
import com.example.fitnesstracker.domain.entity.User
import com.example.fitnesstracker.model.user.LoginUserDto
import com.example.fitnesstracker.model.user.RegisterUserDto
import com.example.fitnesstracker.res.AppStrings
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun login(userDto: LoginUserDto) : Result<User?> {
        val user = userDao.getUserByLogin(userDto.login)
        if (user != null) {
            if (user.password == userDto.password) {
                return Result.success(user)
            }
            return Result.failure(IllegalAccessException(AppStrings.ERROR_PASSWORD_INCORRECT))
        }
        return Result.failure(NullPointerException(AppStrings.ERROR_LOGIN_NOT_EXIST))
    }

    suspend fun register(userDto: RegisterUserDto) : Result<Unit> {
        if (userDao.getUserByLogin(userDto.login) != null) {
            return Result.failure(IllegalArgumentException(AppStrings.ERROR_LOGIN_EXIST))
        }
        userDao.addUser(
            User(
                username = userDto.username,
                login = userDto.login,
                password = userDto.password,
                gender = userDto.gender
            )
        )
        return Result.success(Unit)
    }
}