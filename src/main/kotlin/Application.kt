package com.example

import com.example.domain.service.CityService
import com.example.infrastructure.entity.exposed.CityEntity
import com.example.infrastructure.repository.exposed.ExposedPostgresCityRepository
import com.example.presentation.controller.CityController
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    val databases = configureDatabases()

    val cityRepository = ExposedPostgresCityRepository(
        databases.exposedPostgresMainDatabase
    )
    val cityService = CityService(cityRepository)
    val cityController = CityController(cityService)

    configureRouting(cityController)
}
