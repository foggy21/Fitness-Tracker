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
    );

    companion object {
        fun fromValue(value: String): ActivityType {
            return when (value) {
                AppStrings.BIKE -> Bike
                AppStrings.RUNNING -> Run
                else -> throw IllegalArgumentException(AppStrings.ERROR_ACTIVITY_UNKNOWN)
            }
        }
    }
}