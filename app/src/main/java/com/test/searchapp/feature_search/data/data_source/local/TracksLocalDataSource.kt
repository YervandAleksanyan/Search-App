package com.test.searchapp.feature_search.data.data_source.local

import com.test.searchapp.feature_search.data.model.db.TrackEntity
import com.test.searchapp.feature_search.data.model.db.TrackEntityWithMatchInfo

interface TracksLocalDataSource {

    suspend fun getTracks(): List<TrackEntity>

    suspend fun insertAll(tracks: List<TrackEntity>)

    suspend fun search(query: String): List<TrackEntity>

}