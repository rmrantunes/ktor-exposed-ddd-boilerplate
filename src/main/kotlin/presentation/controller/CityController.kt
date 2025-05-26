package com.example.presentation.controller

import com.example.domain.model.CityModel
import com.example.domain.service.CityService
import com.example.presentation.dto.CityCreateBodyDTO
import com.example.presentation.dto.CityCreateResponseDataDTO
import com.example.presentation.dto.HTTPDataResponseObject

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