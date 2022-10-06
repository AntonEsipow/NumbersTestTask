package com.bigtoapp.numberstesttask.numbers.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NumbersDao {

    @Query("SELECT * FROM numbers_table ORDER BY date DESC")
    fun allNumbers(): List<NumberCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(number: NumberCache)

    @Query("SELECT * FROM numbers_table WHERE number = :number")
    fun number(number: String): NumberCache?
}