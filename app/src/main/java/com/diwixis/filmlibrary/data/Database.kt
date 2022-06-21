package com.diwixis.filmlibrary.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pg.remote.model.MovieBean

@androidx.room.Database(
    entities = [
        com.pg.remote.model.MovieBean::class
    ],
    version = 1,
    exportSchema = true
)
abstract class Database : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        fun create(context: Context): Database = Room
            .databaseBuilder(context, Database::class.java, "FilmLibrary.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}