package com.example.fitnesstracker.model.user

import com.example.fitnesstracker.res.AppStrings

enum class Gender (val gender: String) {
    Male(AppStrings.MALE),
    Female(AppStrings.FEMALE),
    Another(AppStrings.ANOTHER_GENDER);

    companion object {
        fun fromValue(value: String): Gender {
            return when (value){
                AppStrings.MALE -> Male
                AppStrings.FEMALE -> Female
                AppStrings.ANOTHER_GENDER -> Another
                else -> throw IllegalArgumentException(AppStrings.ERROR_GENDER_UNKNOWN)
            }
        }
    }
}