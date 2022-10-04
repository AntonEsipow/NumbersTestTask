package com.bigtoapp.numberstesttask.numbers.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NumberCache::class], version = 1)
abstract class NumbersRoomDatabase: RoomDatabase() {

    abstract fun numbersDao() : NumbersDao
}