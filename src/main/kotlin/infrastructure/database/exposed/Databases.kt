package com.example.infrastructure.database.exposed

import org.jetbrains.exposed.sql.Database

data class ConfigureDatabasesResult (val postgresMainDatabase: Database)

fun configureDatabases(): ConfigureDatabasesResult {
    val h2db = Database.connect(
        url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
        user = "root",
        driver = "org.h2.Driver",
        password = "",
    )

    return ConfigureDatabasesResult(postgresMainDatabase = h2db)
}
