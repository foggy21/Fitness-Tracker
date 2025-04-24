package com.example.fitnesstracker.model.activity

data class ActivityDto(
    val distance: Double,
    val timeDuration: Long,
    val activityType: ActivityType
)
