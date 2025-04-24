package com.example.fitnesstracker.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fitnesstracker.domain.entity.Activity

@Dao
interface ActivityDao {
    @Insert
    fun addActivity(activity: Activity)

    @Query("SELECT * FROM activity")
    fun getAll() : List<Activity>
}