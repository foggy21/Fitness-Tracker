package com.example.fitnesstracker.model.user

import com.example.fitnesstracker.res.AppStrings

enum class Gender (val gender: String) {
    Male(AppStrings.MALE),
    Female(AppStrings.FEMALE),
    Another(AppStrings.ANOTHER_GENDER)
}