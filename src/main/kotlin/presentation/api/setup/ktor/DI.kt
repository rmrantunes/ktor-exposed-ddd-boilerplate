package com.example.presentation.api.setup.ktor

import com.example.domain.service.CityService
import com.example.infrastructure.database.exposed.ConfigureDatabasesResult
import com.example.infrastructure.database.exposed.configureDatabases
import com.example.infrastructure.repository.exposed.ExposedPostgresCityRepository
import com.example.presentation.api.controller.CityController
import io.ktor.server.application.*

data class DIResult(
    val databasesResult: ConfigureDatabasesResult,
    val cityRepository: ExposedPostgresCityRepository,
    val cityService: CityService,
    val cityController: CityController
)

fun Application.setupDI(): DIResult {
    // Databases
    val databases = configureDatabases()

    // CITY
    val cityRepository = ExposedPostgresCityRepository(
        databases.postgresMainDatabase
    )
    val cityService = CityService(cityRepository)
    val cityController = CityController(cityService)

    return DIResult(
        databasesResult = databases,
        cityRepository = cityRepository,
        cityService = cityService,
        cityController = cityController
    )
}