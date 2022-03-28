package com.test.searchapp.feature_search.data.model.network

import com.google.gson.annotations.SerializedName

data class TrackResponse(
    @SerializedName("trackID") val trackId: Int,
    @SerializedName("trackImageURL") val trackImageUrl: String,
    @SerializedName("trackTitle") val trackTitle: String,
    @SerializedName("trackSubtitle") val trackSubtitle: String
)