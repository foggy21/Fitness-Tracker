package com.example.fitnesstracker.domain.converter

import androidx.room.TypeConverter
import com.example.fitnesstracker.model.user.Gender

class GenderConverter {

    @TypeConverter
    fun fromGender(gender: Gender): String {
        return gender.gender
    }

    @TypeConverter
    fun toGender(name: String): Gender {
        return Gender.fromValue(name)
    }
}