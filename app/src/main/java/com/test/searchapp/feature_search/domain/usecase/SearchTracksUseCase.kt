package com.test.searchapp.feature_search.domain.usecase

import com.test.searchapp.core.usecase.CoroutineUseCase
import com.test.searchapp.feature_search.data.repository.TracksRepository
import com.test.searchapp.feature_search.domain.model.TrackDomainModel
import kotlinx.coroutines.CoroutineDispatcher

class SearchTracksUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    private val tracksRepository: TracksRepository
) : CoroutineUseCase<String, List<TrackDomainModel>>(
    coroutineDispatcher
) {

    override suspend fun execute(parameters: String): List<TrackDomainModel> =
        tracksRepository.searchTracks(parameters)
            .map { trackDataModel ->
                TrackDomainModel(
                    trackId = trackDataModel.trackId,
                    trackImageUrl = trackDataModel.trackImageUrl,
                    trackTitle = trackDataModel.trackTitle,
                    trackSubtitle = trackDataModel.trackSubtitle,
                )
            }
}
