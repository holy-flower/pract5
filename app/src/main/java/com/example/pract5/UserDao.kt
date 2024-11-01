package com.example.pract5

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(user: List<User>)

    @Query("SELECT * FROM user")
    suspend fun getAllUsers() : List<User>

    @Query("DELETE FROM user")
    fun deleteAllUsers()
}