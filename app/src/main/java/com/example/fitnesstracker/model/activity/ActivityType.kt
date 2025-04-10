package com.example.fitnesstracker.model.activity

import androidx.annotation.DrawableRes
import com.example.fitnesstracker.R
import com.example.fitnesstracker.res.AppStrings

enum class ActivityType (val activity: String, @DrawableRes val iconRes: Int) {
    Bike(
        activity = AppStrings.BIKE,
        iconRes = R.drawable.bike_activity
    ),
    Run(
        activity = AppStrings.RUNNING,
        iconRes = R.drawable.bike_activity
    )
}