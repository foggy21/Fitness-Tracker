package com.example.fitnesstracker.presentation.state

import com.example.fitnesstracker.model.activity.ActivityType

sealed interface NewActivityState {
    data class Start(val selectedActivity: ActivityType) : NewActivityState
    data class Tracking(
        val distance: Double,
        val time: Long,
        val selectedActivity: ActivityType = ActivityType.Bike
    ) : NewActivityState
}