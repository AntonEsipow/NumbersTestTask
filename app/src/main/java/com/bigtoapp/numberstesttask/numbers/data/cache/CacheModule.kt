package com.bigtoapp.numberstesttask.numbers.data.cache

import android.content.Context
import androidx.room.Room
import com.bigtoapp.numberstesttask.numbers.data.NumberData

interface CacheModule {

    fun provideDatabase(): NumbersRoomDatabase

    class Base(private val context: Context): CacheModule {

        private val database by lazy {
            return@lazy Room.databaseBuilder(
                context,
                NumbersRoomDatabase::class.java,
                "numbers_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        override fun provideDatabase(): NumbersRoomDatabase = database
    }
}