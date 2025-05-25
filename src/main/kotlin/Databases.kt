package com.example

import com.example.infrastructure.table.exposed.CityTable
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases(): Database {
    val database = Database.connect(
        url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
        user = "root",
        driver = "org.h2.Driver",
        password = "",
    )

    initExposed(database)

    return database
}

fun initExposed(database: Database) {
    transaction(database) {
        SchemaUtils.create(CityTable)
    }
}
