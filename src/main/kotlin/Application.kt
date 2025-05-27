package com.example

import com.example.presentation.api.setup.ktor.configureRouting
import com.example.presentation.api.setup.ktor.configureSerialization
import com.example.presentation.api.setup.ktor.setupDI
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    val di = setupDI()
    configureRouting(di.cityController)
}