package com.test.searchapp.feature_search.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.searchapp.feature_search.data.model.db.TrackEntity
import com.test.searchapp.feature_search.data.model.db.TrackEntityWithMatchInfo

@Dao
interface TracksDao {

    @Query("SELECT * FROM tracks")
    suspend fun getTracks(): List<TrackEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(order: List<TrackEntity>)

    @Query(
        """
    SELECT *, matchinfo(tracks_fts, 'pcx') as mi
    FROM tracks
    JOIN tracks_fts ON tracks.trackTitle = tracks_fts.trackTitle OR tracks.trackSubtitle=tracks_fts.trackSubtitle OR tracks.trackId=tracks_fts.trackId
    WHERE tracks_fts MATCH :query
  """
    )
    suspend fun searchTracks(query: String): List<TrackEntityWithMatchInfo>

}