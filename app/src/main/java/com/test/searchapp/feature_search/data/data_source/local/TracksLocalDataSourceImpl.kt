package com.test.searchapp.feature_search.data.data_source.local

import com.test.searchapp.feature_search.data.database.TracksDao
import com.test.searchapp.feature_search.data.model.db.TrackEntity

class TracksLocalDataSourceImpl(
    private val tracksDao: TracksDao
) : TracksLocalDataSource {

    override suspend fun getTracks(): List<TrackEntity> = tracksDao.getTracks()

    override suspend fun insertAll(tracks: List<TrackEntity>) = tracksDao.insertAll(tracks)

    override suspend fun search(
        query: String
    ): List<TrackEntity> = tracksDao.searchTracks(query)
        .sortedByDescending { result -> rank(result.matchInfo.skip(4)) }
        .map { it.track }

    private fun rank(matchInfo: IntArray): Double {
        val numPhrases = matchInfo[0]
        val numColumns = matchInfo[1]

        var score = 0.0
        for (phrase in 0 until numPhrases) {
            val offset = 2 + phrase * numColumns * 3
            for (column in 0 until numColumns) {
                val numHitsInRow = matchInfo[offset + 3 * column]
                val numHitsInAllRows = matchInfo[offset + 3 * column + 1]
                if (numHitsInAllRows > 0) {
                    score += numHitsInRow.toDouble() / numHitsInAllRows.toDouble()
                }
            }
        }

        return score
    }

    private fun ByteArray.skip(skipSize: Int): IntArray {
        val cleanedArr = IntArray(this.size / skipSize)
        for ((pointer, i) in (this.indices step skipSize).withIndex()) {
            cleanedArr[pointer] = this[i].toInt()
        }

        return cleanedArr
    }
}