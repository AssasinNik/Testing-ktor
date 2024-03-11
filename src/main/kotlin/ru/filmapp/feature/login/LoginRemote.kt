package ru.filmapp.feature.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginReceiveRemoteModel(
    val login:String,
    val password: String
)
@Serializable
data class LoginResponseRemoteModel(
    val token:String
)

