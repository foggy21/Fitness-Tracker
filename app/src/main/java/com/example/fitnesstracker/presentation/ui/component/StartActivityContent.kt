package com.example.fitnesstracker.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.model.activity.ActivityType
import com.example.fitnesstracker.res.AppStrings

@Composable
fun StartActivityContent(
    selectedActivity: ActivityType,
    onActivitySelect: (ActivityType) -> Unit,
    onStartClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = AppStrings.LETS_GO,
            style = MaterialTheme.typography.titleLarge
        )

        HorizontalPager(
            state = rememberPagerState(
                initialPage = selectedActivity.ordinal,
                pageCount = { ActivityType.entries.size }
            )
        ) { page ->
            val activity = ActivityType.entries[page]
            ActivityItem(
                activity = activity,
                isSelected = activity == selectedActivity,
                onClick = { onActivitySelect(activity) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        StyledButton(
            onClick = onStartClicked
        ) {
            Text(
                text = AppStrings.START
            )
        }
    }
}