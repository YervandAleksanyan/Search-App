package com.test.searchapp.feature_search.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.test.searchapp.core.model.ViewState
import com.test.searchapp.core.model.map
import com.test.searchapp.core.model.mapResultData
import com.test.searchapp.core.model.toViewState
import com.test.searchapp.core.viewmodel.BaseViewModel
import com.test.searchapp.feature_search.domain.usecase.GetTracksUseCase
import com.test.searchapp.feature_search.domain.usecase.SearchTracksUseCase
import com.test.searchapp.feature_search.presentation.model.TrackPresentationModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getTracksUseCase: GetTracksUseCase,
    private val searchTracksUseCase: SearchTracksUseCase
) : BaseViewModel<List<TrackPresentationModel>>() {

    override suspend fun loadData() {
        loadInitialData()
    }

    private suspend fun loadInitialData() {
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

    fun search(query: String?) {
        viewModelScope.launch {
            if (query.isNullOrBlank()) {
                loadInitialData()
            } else {
                postViewState(ViewState.Loading)
                postViewState(
                    searchTracksUseCase.invoke(sanitizeSearchQuery(query))
                        .map {
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
                        .toViewState { it.isEmpty() }
                )
            }
        }
    }

    private fun sanitizeSearchQuery(query: String?): String {
        if (query == null) {
            return "";
        }
        val queryWithEscapedQuotes = query.replace(Regex.fromLiteral("\""), "\"\"")
        return "*${
            queryWithEscapedQuotes
                .split(" ")
                .filter { it.isNotBlank() }
                .joinToString(" OR ")
        }*"
    }

}