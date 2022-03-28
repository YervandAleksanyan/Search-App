package com.test.searchapp.feature_search.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.searchapp.core.di.GSON
import com.test.searchapp.feature_search.data.model.db.TrackEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import java.lang.reflect.Type

class TrackEntityConverter : KoinComponent {

    private val gson: Gson by inject(named(GSON))

    @TypeConverter
    fun from(value: String): List<TrackEntity> {
        val listType: Type = object : TypeToken<List<TrackEntity>?>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun to(list: List<TrackEntity>): String {
        return gson.toJson(list)
    }
}