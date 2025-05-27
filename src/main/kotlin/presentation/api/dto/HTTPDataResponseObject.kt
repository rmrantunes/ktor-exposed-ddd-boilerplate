package com.example.presentation.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class HTTPDataResponseObject<D>(
    val data: D,
    val statusCode: Int = 200,
    val errors: List<String>? = null
)