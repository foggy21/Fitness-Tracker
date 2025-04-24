package com.example.fitnesstracker.domain.converter

import androidx.room.TypeConverter
import com.example.fitnesstracker.model.activity.ActivityType

class ActivityTypeConverter {
    @TypeConverter
    fun fromActivityType(activityType: ActivityType): String {
        return activityType.activity
    }

    @TypeConverter
    fun toActivityType(activity: String): ActivityType {
        return ActivityType.fromValue(activity)
    }
}