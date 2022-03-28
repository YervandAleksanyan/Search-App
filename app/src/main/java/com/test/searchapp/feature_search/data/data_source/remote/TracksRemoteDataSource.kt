package com.test.searchapp.feature_search.data.data_source.remote

import com.test.searchapp.feature_search.data.model.network.TrackResponse

interface TracksRemoteDataSource {
    suspend fun getTracks(): List<TrackResponse>
}