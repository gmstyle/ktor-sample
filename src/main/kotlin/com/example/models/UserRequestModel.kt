package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class UserRequestModel(
    val nome: String?,
    val cognome: String?
)
