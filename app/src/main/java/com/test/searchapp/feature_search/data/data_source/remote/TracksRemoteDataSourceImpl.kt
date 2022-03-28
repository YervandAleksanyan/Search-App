package com.test.searchapp.feature_search.data.data_source.remote

import com.test.searchapp.feature_search.data.model.network.TrackResponse
import com.test.searchapp.feature_search.data.network.TracksApi

class TracksRemoteDataSourceImpl(
    private val tracksApi: TracksApi
) : TracksRemoteDataSource {

    override suspend fun getTracks(): List<TrackResponse> = tracksApi.getTracks()

}