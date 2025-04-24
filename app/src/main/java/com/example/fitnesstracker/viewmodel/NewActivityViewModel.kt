package com.example.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fitnesstracker.data.database.ActivityRepository
import com.example.fitnesstracker.model.activity.ActivityDto
import com.example.fitnesstracker.model.activity.ActivityType
import com.example.fitnesstracker.presentation.state.NewActivityState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class NewActivityViewModel @Inject constructor(
    private val activityRepository: ActivityRepository
) : ViewModel() {
    private val _newActivityState = MutableStateFlow<NewActivityState>(
        NewActivityState.Start(ActivityType.Bike)
    )
    val newActivityState: StateFlow<NewActivityState> = _newActivityState

    fun selectActivity(type: ActivityType) {
        if (_newActivityState.value is NewActivityState.Start)
        {
            _newActivityState.value = NewActivityState.Start(
                selectedActivity = type
            )
        }
    }

    fun startTracking() {
        _newActivityState.value = NewActivityState.Tracking(
            distance = Random.nextDouble(0.0, 10000.0),
            time = Random.nextLong(0, 25000)
        )
    }

    fun stopTracking() {
        if (_newActivityState.value is NewActivityState.Tracking) {
            activityRepository.addNewActivity(
                ActivityDto(
                    distance = (_newActivityState.value as NewActivityState.Tracking).distance,
                    timeDuration = (_newActivityState.value as NewActivityState.Tracking).time,
                    activityType = (_newActivityState.value as NewActivityState.Tracking).selectedActivity
                )
            )
            _newActivityState.value = NewActivityState.Start(
                selectedActivity = (_newActivityState.value as NewActivityState.Tracking).selectedActivity
            )
        }
    }
}