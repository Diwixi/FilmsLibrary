package com.diwixis.filmlibrary.data

import androidx.room.*
import com.pg.remote.model.MovieBean
import com.pg.remote.model.MovieBean.Companion.TABLE_MOVIE

@Dao
interface MovieDao {

    @Query("SELECT * FROM $TABLE_MOVIE")
    suspend fun getAll(): List<com.pg.remote.model.MovieBean>

//    @Query("SELECT * FROM $TABLE_MOVIE)
//    suspend fun getTop(): List<MovieBean>
//
//    @Query("SELECT * FROM $TABLE_MOVIE)
//    suspend fun getPop(): List<MovieBean>

    @Query("SELECT * FROM $TABLE_MOVIE WHERE id = :id")
    suspend fun getById(id: Int): com.pg.remote.model.MovieBean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<com.pg.remote.model.MovieBean>)

    @Query("DELETE FROM $TABLE_MOVIE")
    suspend fun deleteAll()

    @Transaction
    suspend fun swap(list: List<com.pg.remote.model.MovieBean>) {
        deleteAll()
        insertAll(list)
    }
}