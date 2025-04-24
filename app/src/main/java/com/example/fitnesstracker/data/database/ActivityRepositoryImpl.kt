package com.example.fitnesstracker.data.database

import com.example.fitnesstracker.domain.dao.ActivityDao
import com.example.fitnesstracker.domain.entity.Activity
import com.example.fitnesstracker.model.activity.ActivityDto
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val activityDao: ActivityDao
) : ActivityRepository {
    override fun getAllActivities(): Result<List<Activity>?> {
        try {
            val activities = activityDao.getAll()
            return Result.success(activities)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override fun addNewActivity(activityDto: ActivityDto): Result<Unit> {
        try {
            activityDao.addActivity(
                Activity(
                    distance = activityDto.distance,
                    timeDuration = activityDto.timeDuration,
                    activityType = activityDto.activityType
                )
            )
            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}