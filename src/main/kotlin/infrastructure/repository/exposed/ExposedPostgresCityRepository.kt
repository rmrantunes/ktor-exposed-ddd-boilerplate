package com.example.infrastructure.repository.exposed

import com.example.domain.model.CityModel
import com.example.domain.repository.CityRepository
import com.example.infrastructure.entity.exposed.CityEntity
import com.example.infrastructure.entity.exposed.toModel
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class ExposedPostgresCityRepository(private val db: Database) :
    CityRepository {

    override suspend fun create(city: CityModel): Int = dbQuery(db) {
        CityEntity.new {
            name = city.name
            population = city.population
        }.id.value
    }


    override suspend fun read(id: Int): CityModel? = dbQuery(db) {
        val city = CityEntity.findById(id)
        city?.toModel() ?: return@dbQuery null
    }


    override suspend fun update(id: Int, city: CityModel) = dbQuery(db) {
        CityEntity.findByIdAndUpdate(id) {
            it.name = city.name
            it.population = city.population
        }
        return@dbQuery
    }

    override suspend fun delete(id: Int): Boolean? = dbQuery(db) {
        val city = CityEntity.findById(id)
        if (city == null) return@dbQuery null
        else {
            city.delete()
            return@dbQuery true
        }
    }

    private suspend fun <T> dbQuery(db: Database? = null, block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO, db) { block() }
}