package com.diwixis.feature_movie_list.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(
    entities = [
        MovieBean::class
    ],
    version = 1,
    exportSchema = true
)
abstract class Database : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        fun create(context: Context) = Room
            .databaseBuilder(context, Database::class.java, "FilmsLibrary.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}