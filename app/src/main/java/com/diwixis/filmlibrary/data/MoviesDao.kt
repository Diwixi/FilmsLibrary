package com.diwixis.filmlibrary.data

import androidx.room.*
import com.diwixis.filmlibrary.data.MovieBean.Companion.TABLE_MOVIE

@Dao
interface MovieDao {

    @Query("SELECT * FROM $TABLE_MOVIE")
    suspend fun getAll(): List<MovieBean>

//    @Query("SELECT * FROM $TABLE_MOVIE)
//    suspend fun getTop(): List<MovieBean>
//
//    @Query("SELECT * FROM $TABLE_MOVIE)
//    suspend fun getPop(): List<MovieBean>

    @Query("SELECT * FROM $TABLE_MOVIE WHERE id = :id")
    suspend fun getById(id: Int): MovieBean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<MovieBean>)

    @Query("DELETE FROM $TABLE_MOVIE")
    suspend fun deleteAll()

    @Transaction
    suspend fun swap(list: List<MovieBean>) {
        deleteAll()
        insertAll(list)
    }
}