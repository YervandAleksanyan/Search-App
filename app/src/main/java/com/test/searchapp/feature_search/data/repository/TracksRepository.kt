package com.test.searchapp.feature_search.data.repository

import com.test.searchapp.core.model.Result
import com.test.searchapp.feature_search.data.model.TrackDataModel
import kotlinx.coroutines.flow.Flow

interface TracksRepository {
    fun getTracks(): Flow<Result<List<TrackDataModel>>>
    suspend fun searchTracks(query: String): List<TrackDataModel>
}