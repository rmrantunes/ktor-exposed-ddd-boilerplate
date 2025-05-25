package com.example.infrastructure.entity.exposed

import com.example.domain.model.CityModel
import com.example.infrastructure.table.exposed.CityTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CityEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CityEntity>(CityTable)

    var name by CityTable.name
    var population by CityTable.population
}

fun CityEntity.toModel(): CityModel {
    return CityModel(this.id.value, this.name, this.population)
}
