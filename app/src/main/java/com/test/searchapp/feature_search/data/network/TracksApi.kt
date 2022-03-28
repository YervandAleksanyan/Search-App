package com.test.searchapp.feature_search.data.network

import com.test.searchapp.feature_search.data.model.network.TrackResponse

interface TracksApi {
    suspend fun getTracks(): List<TrackResponse>
}