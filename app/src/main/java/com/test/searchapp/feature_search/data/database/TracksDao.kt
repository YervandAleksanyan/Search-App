package com.test.searchapp.feature_search.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.searchapp.feature_search.data.model.db.TrackEntity

@Dao
interface TracksDao {

    @Query("SELECT * FROM tracks")
    suspend fun getTracks(): List<TrackEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(order: List<TrackEntity>)

}