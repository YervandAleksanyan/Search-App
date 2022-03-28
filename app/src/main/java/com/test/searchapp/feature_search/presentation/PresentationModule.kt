package com.test.searchapp.feature_search.presentation

import com.test.searchapp.feature_search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        SearchViewModel(
            getTracksUseCase = get(),
            searchTracksUseCase = get()
        )
    }

}