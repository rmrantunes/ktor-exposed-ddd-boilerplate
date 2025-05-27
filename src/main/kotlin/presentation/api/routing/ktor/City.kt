package com.example.presentation.api.routing.ktor

import com.example.presentation.api.controller.CityController
import com.example.presentation.api.dto.CityCreateBodyDTO
import com.example.presentation.api.dto.CityGetParamsDTO
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking

fun Routing.cityRoutes(cityController: CityController) {
    post("/cities") {
        val result = cityController.create(onGetBody = {
            runBlocking {
                call.receive<CityCreateBodyDTO>()
            }
        })
        call.respond(HttpStatusCode.fromValue(result.statusCode), result)
    }

    get("/cities/{id}") {
        val result = cityController.get(onGetParams = {
            val id = call.parameters["id"]?.toInt() ?: 0
            CityGetParamsDTO(id)
        })

        call.respond(HttpStatusCode.fromValue(result.statusCode), result)
    }
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