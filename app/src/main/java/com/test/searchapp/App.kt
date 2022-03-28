package com.test.searchapp

import android.app.Application
import com.test.searchapp.core.di.coroutineModule
import com.test.searchapp.core.di.databaseModule
import com.test.searchapp.feature_search.data.dataModule
import com.test.searchapp.feature_search.domain.domainModule
import com.test.searchapp.feature_search.presentation.presentationModule
import com.test.searchapp.utils.NumberedTimberTree
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    databaseModule,
                    coroutineModule,
                    dataModule,
                    domainModule,
                    presentationModule
                )
            )
        }
        // Logger
        if (BuildConfig.DEBUG) {
            Timber.plant(NumberedTimberTree())
        } else {
            Timber.plant(object : Timber.Tree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    // ignore
                }
            })
        }
    }
}