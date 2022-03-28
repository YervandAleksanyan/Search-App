package com.test.searchapp.core.di

import androidx.room.Room
import com.google.gson.Gson
import com.test.searchapp.core.database.AppDatabase
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val GSON = "gson"
const val APP_DATABASE_NAME = "search-app.db"

val databaseModule = module {

    single(named(GSON)) { Gson() }

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, APP_DATABASE_NAME
        ).build()
    }

}