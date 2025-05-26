package com.example.presentation.api.setup.ktor

import io.ktor.server.application.*

fun Application.module() {
    configureSerialization()
    val di = setupDI()
    configureRouting(di.cityController)
}