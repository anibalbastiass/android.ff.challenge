package com.ff.challenge.feature.auth.presentation.mapper

import com.ff.challenge.feature.auth.domain.model.DomainUser
import com.ff.challenge.feature.auth.presentation.model.UiUser

class UiUserMapper {

    fun DomainUser.fromDomainToUi() = UiUser(
        userId = userId,
        fullName = fullName,
        email = email,
        password = password
    )
}