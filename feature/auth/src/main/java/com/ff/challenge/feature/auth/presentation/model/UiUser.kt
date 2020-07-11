package com.ff.challenge.feature.auth.presentation.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class UiUser(
    val userId: String,
    val fullName: String,
    val email: String,
    val password: String
) : Parcelable