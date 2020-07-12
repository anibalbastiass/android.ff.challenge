package com.ff.challenge.feature.auth.domain.model

data class DomainUser(
    val userId: String,
    val fullName: String,
    val email: String,
    val password: String
)