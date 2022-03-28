package com.test.searchapp.feature_search.data.data_source.local

import com.test.searchapp.feature_search.data.model.db.TrackEntity

interface TracksLocalDataSource {

    suspend fun getTracks(): List<TrackEntity>

    suspend fun insertAll(tracks: List<TrackEntity>)

}