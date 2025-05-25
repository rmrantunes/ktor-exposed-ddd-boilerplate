package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CityModel(val id: Int?, val name: String, val population: Int)
