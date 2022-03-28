package com.test.searchapp.feature_search.data.model.db

import androidx.room.*

@Entity(tableName = "tracks_fts")
@Fts4(contentEntity = TrackEntity::class)
data class TrackEntityFts(
    @ColumnInfo(name = "trackId")
    val trackId: Int,
    @ColumnInfo(name = "trackImageUrl")
    val trackImageUrl: String,
    @ColumnInfo(name = "trackTitle")
    val trackTitle: String,
    @ColumnInfo(name = "trackSubtitle")
    val trackSubtitle: String
)