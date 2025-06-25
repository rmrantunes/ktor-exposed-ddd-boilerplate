package com.example.testutils.repository

import com.example.domain.model.CityModel
import com.example.domain.repository.CityRepository
import com.example.domain.repository.Total

class FakeCityRepository : CityRepository {
    private val table = mutableMapOf<Int?, CityModel>()

    override suspend fun create(city: CityModel): Int {
        val currentKey = table.keys.lastOrNull() ?: 0
        val nextKey = currentKey + 1
        table[nextKey] = city
        return nextKey
    }

    override suspend fun read(id: Int): CityModel? {
        return table[id]
    }

    override suspend fun update(id: Int, city: CityModel) {
        val record = read(id)
        if (record != null) {
            table[id] = record.copy(
                name = city.name,
                population = city.population
            )
        }
    }

    override suspend fun delete(id: Int): Boolean? {
        table.remove(id)
        return true
    }

    override suspend fun list(): Pair<List<CityModel>, Total> {
        return Pair(table.values.toList(), table.size)
    }
}