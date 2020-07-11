package com.ff.challenge.feature.auth.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ff.challenge.feature.auth.data.local.Constants.DATABASE_VERSION
import com.ff.challenge.feature.auth.data.local.dao.UsersDao
import com.ff.challenge.feature.auth.domain.local.model.EntityUser

@Database(
    entities = [
        EntityUser::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AuthDatabase : RoomDatabase() {
    abstract val users: UsersDao
}