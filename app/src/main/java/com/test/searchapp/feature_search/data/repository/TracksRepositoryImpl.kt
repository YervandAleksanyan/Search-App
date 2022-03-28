package com.test.searchapp.feature_search.data.repository

import com.test.searchapp.core.model.Result
import com.test.searchapp.core.repository.MultiSourceRepository
import com.test.searchapp.core.repository.cacheElseNetwork
import com.test.searchapp.feature_search.data.data_source.local.TracksLocalDataSource
import com.test.searchapp.feature_search.data.data_source.remote.TracksRemoteDataSource
import com.test.searchapp.feature_search.data.model.TrackDataModel
import com.test.searchapp.feature_search.data.model.db.TrackEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TracksRepositoryImpl(
    private val tracksLocalDataSource: TracksLocalDataSource,
    private val tracksRemoteDataSource: TracksRemoteDataSource
) : TracksRepository, MultiSourceRepository {

    override fun getTracks(): Flow<Result<List<TrackDataModel>>> {
        return cacheElseNetwork(
            cacheCall = {
                tracksLocalDataSource.getTracks()
                    .map {
                        TrackDataModel(
                            trackId = it.trackId,
                            trackImageUrl = it.trackImageUrl,
                            trackTitle = it.trackTitle,
                            trackSubtitle = it.trackSubtitle
                        )
                    }
            },
            networkCall = {
                tracksRemoteDataSource.getTracks()
                    .map { trackResponse ->
                        TrackDataModel(
                            trackId = trackResponse.trackId,
                            trackImageUrl = trackResponse.trackImageUrl,
                            trackTitle = trackResponse.trackTitle,
                            trackSubtitle = trackResponse.trackSubtitle
                        )
                    }
            },
            updateCache = {
                tracksLocalDataSource.insertAll(it.map { trackDataModel ->
                    TrackEntity(
                        trackId = trackDataModel.trackId,
                        trackImageUrl = trackDataModel.trackImageUrl,
                        trackTitle = trackDataModel.trackTitle,
                        trackSubtitle = trackDataModel.trackSubtitle
                    )
                })
            }
        )
    }

    override suspend fun searchTracks(query: String): List<TrackDataModel> =
        tracksLocalDataSource.search(query)
            .map { trackEntity ->
                TrackDataModel(
                    trackId = trackEntity.trackId,
                    trackImageUrl = trackEntity.trackImageUrl,
                    trackTitle = trackEntity.trackTitle,
                    trackSubtitle = trackEntity.trackSubtitle
                )
            }

}