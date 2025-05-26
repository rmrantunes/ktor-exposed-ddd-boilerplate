package com.example.presentation.dto

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
