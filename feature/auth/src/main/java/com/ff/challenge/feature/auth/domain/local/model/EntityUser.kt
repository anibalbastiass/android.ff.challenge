package com.ff.challenge.feature.auth.domain.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ff.challenge.feature.auth.data.local.Constants.TABLE_USERS

@Entity(tableName = TABLE_USERS)
data class EntityUser(
    @PrimaryKey
    val userId: String,
    val fullName: String,
    val email: String,
    val password: String
)
