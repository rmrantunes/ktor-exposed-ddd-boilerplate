package com.example.presentation.api.controller

import com.example.domain.model.CityModel
import com.example.domain.service.CityService
import com.example.presentation.api.dto.CityCreateBodyDTO
import com.example.presentation.api.dto.CityCreateResponseDataDTO
import com.example.presentation.api.dto.CityGetParamsDTO
import com.example.presentation.api.dto.HTTPDataResponseObject
import com.example.presentation.api.enumeration.StatusCode

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
            statusCode = StatusCode.CREATED
        )
    }

    suspend fun get(
        onGetParams: () -> CityGetParamsDTO
    ): HTTPDataResponseObject<CityModel?> {
        val params = onGetParams()
        val city = service.read(params.id)

        return HTTPDataResponseObject(
            city,
            statusCode = if (city != null) StatusCode.OK else StatusCode.NOT_FOUND,
            errors = if (city != null) null else listOf("City not found")
        )
    }
}