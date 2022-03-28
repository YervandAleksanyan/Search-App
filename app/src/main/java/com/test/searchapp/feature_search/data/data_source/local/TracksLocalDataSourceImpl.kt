package com.test.searchapp.feature_search.data.data_source.local

import com.test.searchapp.feature_search.data.database.TracksDao
import com.test.searchapp.feature_search.data.model.db.TrackEntity

class TracksLocalDataSourceImpl(
    private val tracksDao: TracksDao
) : TracksLocalDataSource {

    override suspend fun getTracks(): List<TrackEntity> = tracksDao.getTracks()

    override suspend fun insertAll(tracks: List<TrackEntity>)  = tracksDao.insertAll(tracks)
}