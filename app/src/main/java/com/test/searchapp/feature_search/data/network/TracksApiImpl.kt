package com.test.searchapp.feature_search.data.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.searchapp.feature_search.data.model.network.TrackResponse
import com.test.searchapp.utils.JsonUtils

class TracksApiImpl(
    private val gson: Gson,
    private val context: Context
) : TracksApi {

    private val sourceName = "tracks.json"

    override suspend fun getTracks(): List<TrackResponse> {
        val jsonFileString = JsonUtils.getJsonDataFromAsset(context, sourceName)
        val type = object : TypeToken<List<TrackResponse>>() {}.type
        return gson.fromJson(jsonFileString, type)
    }

}