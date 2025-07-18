package com.example.domain.repository

import com.example.domain.model.CityModel

typealias Total = Int

interface CityRepository {
    suspend fun create(city: CityModel): Int
    suspend fun read(id: Int): CityModel?
    suspend fun list(): Pair<List<CityModel>, Total>
    suspend fun update(id: Int, city: CityModel)
    suspend fun delete(id: Int): Boolean?
}