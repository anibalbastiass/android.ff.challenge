package com.ff.challenge.feature.auth.data.local.dao

import androidx.room.*
import com.ff.challenge.feature.auth.domain.local.model.EntityUser

@Dao
interface UsersDao {

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun getUserByEmailPassword(email: String, password: String): EntityUser

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user: EntityUser)

    @Delete
    suspend fun deleteUser(vararg user: EntityUser)

}