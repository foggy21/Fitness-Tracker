package com.example.fitnesstracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fitnesstracker.domain.converter.GenderConverter
import com.example.fitnesstracker.domain.dao.UserDao
import com.example.fitnesstracker.domain.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(GenderConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}