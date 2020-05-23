package com.diwixis.filmlibrary.data

import androidx.room.*
import com.diwixis.filmlibrary.data.MovieBean.Companion.TABLE_MOVIE
import io.reactivex.Single

/**
 * Сохранять сущности в базу и отдельно хранить списки id для top_rated
 * и для popular!
 *
 * @author П. Густокашин (Diwixis)
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM $TABLE_MOVIE")
    fun getAll(): Single<List<MovieBean>>

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