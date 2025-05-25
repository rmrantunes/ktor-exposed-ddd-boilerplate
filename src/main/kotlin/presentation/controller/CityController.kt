package com.example.presentation.controller

import com.example.domain.model.CityModel
import com.example.domain.service.CityService

class CityController (
    private val service: CityService
){
    suspend fun create(
        onGetBody: () -> CityModel
    ): Int {
        val city = onGetBody()
        return service.create(city)
    }
}