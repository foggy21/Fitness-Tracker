package com.example.fitnesstracker.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitnesstracker.model.activity.ActivityType

@Entity
data class Activity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "distance")
    val distance: Double,
    @ColumnInfo(name = "time_duration")
    val timeDuration: Long,
    @ColumnInfo(name = "activity_type")
    val activityType: ActivityType
)
