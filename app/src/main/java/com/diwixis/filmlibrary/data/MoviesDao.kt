package com.diwixis.filmlibrary.data

import androidx.room.*
import com.diwixis.filmlibrary.data.MovieBean.Companion.COLUMN_NAME_MODE
import com.diwixis.filmlibrary.data.MovieBean.Companion.TABLE_MOVIE
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM $TABLE_MOVIE")
    fun getAll(): Single<List<MovieBean>>

    @Query("SELECT * FROM $TABLE_MOVIE WHERE $COLUMN_NAME_MODE = 'MODE_TOP'")
    fun getTop(): Single<List<MovieBean>>

    @Query("SELECT * FROM $TABLE_MOVIE WHERE $COLUMN_NAME_MODE = 'MODE_POP'")
    fun getPop(): Single<List<MovieBean>>

    @Query("SELECT * FROM $TABLE_MOVIE WHERE id = :id")
    fun getById(id: Int): Single<MovieBean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<MovieBean>)

    @Query("DELETE FROM $TABLE_MOVIE")
    fun deleteAll()

    @Transaction
    fun swap(list: List<MovieBean>) {
        deleteAll()
        insertAll(list)
    }
}