package com.example.fitnesstracker.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fitnesstracker.domain.entity.User

@Dao
interface UserDao {
    @Insert
    fun addUser(user: User)

    @Query("SELECT * FROM user WHERE login = :login ")
    fun getUserByLogin(login: String): User?

    @Query("SELECT * FROM user WHERE username = :username")
    fun getUserByUsername(username: String): User?
}