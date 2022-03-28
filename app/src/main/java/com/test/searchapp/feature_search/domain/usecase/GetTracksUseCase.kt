package com.test.searchapp.feature_search.domain.usecase

import com.test.searchapp.core.model.Result
import com.test.searchapp.core.model.mapResultData
import com.test.searchapp.core.usecase.FlowUseCase
import com.test.searchapp.feature_search.data.repository.TracksRepository
import com.test.searchapp.feature_search.domain.model.TrackDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetTracksUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    private val tracksRepository: TracksRepository
) : FlowUseCase<Unit, List<TrackDomainModel>>(
    coroutineDispatcher
) {

    override fun execute(
        parameters: Unit
    ): Flow<Result<List<TrackDomainModel>>> = tracksRepository.getTracks()
        .mapResultData {
            it
                .map { trackDataModel ->
                    TrackDomainModel(
                        trackId = trackDataModel.trackId,
                        trackImageUrl = trackDataModel.trackImageUrl,
                        trackTitle = trackDataModel.trackTitle,
                        trackSubtitle = trackDataModel.trackSubtitle,
                    )
                }
                .sortedBy { track -> track.trackId }
        }

}