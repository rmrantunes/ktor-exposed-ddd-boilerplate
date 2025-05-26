package com.example.presentation.dto

import kotlinx.serialization.Serializable

@Serializable
data class HTTPDataResponseObject<D> (
    val data: D,
    val statusCode: Int = 200
)