package com.test.searchapp.feature_search.data

import com.test.searchapp.core.di.GSON
import com.test.searchapp.core.database.AppDatabase
import com.test.searchapp.feature_search.data.data_source.local.TracksLocalDataSource
import com.test.searchapp.feature_search.data.data_source.local.TracksLocalDataSourceImpl
import com.test.searchapp.feature_search.data.data_source.remote.TracksRemoteDataSource
import com.test.searchapp.feature_search.data.data_source.remote.TracksRemoteDataSourceImpl
import com.test.searchapp.feature_search.data.repository.TracksRepository
import com.test.searchapp.feature_search.data.repository.TracksRepositoryImpl
import com.test.searchapp.feature_search.data.network.TracksApi
import com.test.searchapp.feature_search.data.network.TracksApiImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    factory {
        val appDatabase: AppDatabase = get()
        appDatabase.searchDao()
    }

    factory<TracksApi> { TracksApiImpl(get(named(GSON)), androidContext()) }

    factory<TracksLocalDataSource> { TracksLocalDataSourceImpl(get()) }

    factory<TracksRemoteDataSource> { TracksRemoteDataSourceImpl(get()) }

    single<TracksRepository> { TracksRepositoryImpl(get(), get()) }

}
