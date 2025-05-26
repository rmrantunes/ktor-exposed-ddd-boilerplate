package com.example.domain.service

import com.example.domain.model.CityModel
import com.example.domain.repository.CityRepository

class CityService(
    private val cityRepository: CityRepository
) {
    suspend fun create(city: CityModel): Int {
        return cityRepository.create(city)
    }

    suspend fun read(id: Int): CityModel? {
        return cityRepository.read(id)
    }

    suspend fun update(id: Int, city: CityModel) {
        return cityRepository.update(id, city)
    }

    suspend fun delete(id: Int): Boolean? {
        return cityRepository.delete(id)
    }
}