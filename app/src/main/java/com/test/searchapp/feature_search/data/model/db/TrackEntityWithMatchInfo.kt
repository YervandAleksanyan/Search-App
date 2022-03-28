package com.test.searchapp.feature_search.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class TrackEntityWithMatchInfo(
    @Embedded
    val track: TrackEntity,
    @ColumnInfo(name = "mi")
    val matchInfo: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TrackEntityWithMatchInfo

        if (track != other.track) return false
        if (!matchInfo.contentEquals(other.matchInfo)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = track.hashCode()
        result = 31 * result + matchInfo.contentHashCode()
        return result
    }
}
