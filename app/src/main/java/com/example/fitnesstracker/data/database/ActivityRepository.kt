package com.example.fitnesstracker.data.database

import com.example.fitnesstracker.domain.entity.Activity
import com.example.fitnesstracker.model.activity.ActivityDto

interface ActivityRepository {
    fun getAllActivities() : Result<List<Activity>?>
    fun addNewActivity(activityDto: ActivityDto): Result<Unit>
}