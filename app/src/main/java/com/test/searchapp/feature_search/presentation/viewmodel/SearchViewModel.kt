package com.test.searchapp.feature_search.presentation.viewmodel

import com.test.searchapp.core.model.mapResultData
import com.test.searchapp.core.model.toViewState
import com.test.searchapp.core.viewmodel.BaseViewModel
import com.test.searchapp.feature_search.domain.usecase.GetTracksUseCase
import com.test.searchapp.feature_search.presentation.model.TrackPresentationModel
import kotlinx.coroutines.flow.first

class SearchViewModel(
    private val getTracksUseCase: GetTracksUseCase
) : BaseViewModel<List<TrackPresentationModel>>() {

    override suspend fun loadData() {
        postViewState(
            getTracksUseCase(Unit)
                .mapResultData {
                    it
                        .map { trackDomainModel ->
                            TrackPresentationModel(
                                trackId = trackDomainModel.trackId,
                                trackImageUrl = trackDomainModel.trackImageUrl,
                                trackTitle = trackDomainModel.trackTitle,
                                trackSubtitle = trackDomainModel.trackSubtitle,
                            )
                        }
                }
                .first()
                .toViewState { it.isEmpty() }
        )
    }

}