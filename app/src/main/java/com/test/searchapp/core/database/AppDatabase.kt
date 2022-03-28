package com.test.searchapp.core.database

import androidx.room.Database
import androidx.room.Fts4
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.searchapp.feature_search.data.database.TrackEntityConverter
import com.test.searchapp.feature_search.data.database.TracksDao
import com.test.searchapp.feature_search.data.model.db.TrackEntity
import com.test.searchapp.feature_search.data.model.db.TrackEntityFts

@Database(
    entities = [
        TrackEntity::class,
        TrackEntityFts::class
    ],
    exportSchema = false,
    version = 1,
)
@TypeConverters(value = [TrackEntityConverter::class])
@Fts4
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchDao(): TracksDao

}