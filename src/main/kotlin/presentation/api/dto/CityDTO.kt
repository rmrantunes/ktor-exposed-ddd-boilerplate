package com.example.presentation.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class CityCreateBodyDTO(
    val name: String,
    val population: Int
)

@Serializable
data class CityCreateResponseDataDTO(
    val id: Int
)

@Serializable
data class CityGetParamsDTO(
    val id: Int
)