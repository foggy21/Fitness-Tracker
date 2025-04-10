package com.example.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fitnesstracker.model.activity.ActivityType
import com.example.fitnesstracker.presentation.state.NewActivityState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NewActivityViewModel : ViewModel() {
    private val _newActivityState = MutableStateFlow<NewActivityState>(
        NewActivityState.Start(ActivityType.Bike)
    )
    val newActivityState: StateFlow<NewActivityState> = _newActivityState

    fun selectActivity(type: ActivityType) {
        _newActivityState.value = (_newActivityState.value as NewActivityState.Start)
            .copy(selectedActivity = type)
    }

    fun startTracking() {
        _newActivityState.value = NewActivityState.Tracking(
            distance = "14",
            time = "00:01:15"
        )
    }

    fun stopTracking() {
        _newActivityState.value = NewActivityState.Start(
            selectedActivity = (_newActivityState.value as NewActivityState.Tracking).selectedActivity
        )
    }
}