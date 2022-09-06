package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseModel<T>(
    val result: Boolean,
    val data: T?,
    val message: String?
)
