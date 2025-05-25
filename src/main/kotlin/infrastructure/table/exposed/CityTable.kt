package com.example.infrastructure.table.exposed

import org.jetbrains.exposed.dao.id.IntIdTable

const val MAX_VARCHAR_LENGTH = 50

object CityTable : IntIdTable() {
    val population = integer("population")
    val name = varchar("name", MAX_VARCHAR_LENGTH)
}
