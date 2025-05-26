package com.example.presentation.api.controller

import com.example.domain.model.CityModel
import com.example.domain.service.CityService
import com.example.presentation.api.dto.CityCreateBodyDTO
import com.example.presentation.api.dto.CityCreateResponseDataDTO
import com.example.presentation.api.dto.HTTPDataResponseObject

class CityController(
    private val service: CityService
) {
    suspend fun create(
        onGetBody: () -> CityCreateBodyDTO
    ): HTTPDataResponseObject<CityCreateResponseDataDTO> {
        val city = onGetBody()
        val id = service.create(CityModel(id = null, name = city.name, population = city.population))
        return HTTPDataResponseObject(
            CityCreateResponseDataDTO(id),
            statusCode = 201
        )
    }
}