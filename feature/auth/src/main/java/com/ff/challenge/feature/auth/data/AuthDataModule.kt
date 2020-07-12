package com.ff.challenge.feature.auth.data

import androidx.room.Room
import com.ff.challenge.app.FFApplication
import com.ff.challenge.feature.auth.MODULE_NAME
import com.ff.challenge.feature.auth.data.local.AuthDatabase
import com.ff.challenge.feature.auth.data.local.Constants.DATABASE_ASSETS
import com.ff.challenge.feature.auth.data.local.Constants.DATABASE_NAME
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

internal val dataModule = Kodein.Module("${MODULE_NAME}DataModule") {

    bind() from singleton {
        Room.databaseBuilder(
            FFApplication.appContext,
            AuthDatabase::class.java,
            DATABASE_NAME
        ).createFromAsset(DATABASE_ASSETS).allowMainThreadQueries().build()
    }
}
