package com.example.pract5

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    @SerializedName("birth_date") val birthDate: String,
    val gender: String,
    val age: Int
)