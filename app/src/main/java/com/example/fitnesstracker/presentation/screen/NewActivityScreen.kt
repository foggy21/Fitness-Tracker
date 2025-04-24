package com.example.fitnesstracker.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstracker.presentation.state.NewActivityState
import com.example.fitnesstracker.presentation.ui.component.StartActivityContent
import com.example.fitnesstracker.presentation.ui.component.TrackingActivityContent
import com.example.fitnesstracker.viewmodel.NewActivityViewModel

@Composable
fun NewActivityScreen(
    viewModel: NewActivityViewModel = hiltViewModel(),
) {
    val newActivityState by viewModel.newActivityState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        when(newActivityState){
            is NewActivityState.Start -> {
                StartActivityContent(
                    selectedActivity = (newActivityState as NewActivityState.Start).selectedActivity,
                    onActivitySelect = viewModel::selectActivity,
                    onStartClicked = viewModel::startTracking
                )
            }
            is NewActivityState.Tracking -> {
                TrackingActivityContent(
                    distance = (newActivityState as NewActivityState.Tracking).distance.toString(),
                    time = (newActivityState as NewActivityState.Tracking).time.toString(),
                    onStopClicked = viewModel::stopTracking
                )
            }
        }
    }
}