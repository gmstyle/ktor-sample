package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class DeviceRequestModel(
    val nome: String,
    val userIdProprietario: Int
)
