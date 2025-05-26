package com.example

import com.example.presentation.controller.CityController
import com.example.presentation.dto.CityCreateBodyDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking

fun Application.configureRouting(
    cityController: CityController
) {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }

    routing {
        // Create city
        post("/cities") {
            val result = cityController.create(onGetBody = {
                runBlocking {
                    call.receive<CityCreateBodyDTO>()
                }
            })
            call.respond(HttpStatusCode.fromValue(result.statusCode), result)
        }

//        // Read city
//        get("/cities/{id}") {
//            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
//            try {
//                val city = cityService.read(id)
//                call.respond(HttpStatusCode.OK, city)
//            } catch (e: Exception) {
//                call.respond(HttpStatusCode.NotFound)
//            }
//        }
//
//        // Update city
//        put("/cities/{id}") {
//            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
//            val user = call.receive<City>()
//            cityService.update(id, user)
//            call.respond(HttpStatusCode.OK)
//        }
//
//        // Delete city
//        delete("/cities/{id}") {
//            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
//            cityService.delete(id)
//            call.respond(HttpStatusCode.OK)
//        }
    }
}
