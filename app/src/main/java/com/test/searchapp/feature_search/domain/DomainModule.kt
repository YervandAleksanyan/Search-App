package com.test.searchapp.feature_search.domain

import com.test.searchapp.core.di.DispatchersName
import com.test.searchapp.feature_search.domain.usecase.GetTracksUseCase
import com.test.searchapp.feature_search.domain.usecase.SearchTracksUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetTracksUseCase(
            coroutineDispatcher = get(qualifier = named(DispatchersName.IO)),
            tracksRepository = get(),
        )
    }

    factory {
        SearchTracksUseCase(
            coroutineDispatcher = get(qualifier = named(DispatchersName.IO)),
            tracksRepository = get(),
        )
    }

}