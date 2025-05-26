package com.example.api.ktor

import com.example.domain.model.CityModel
import com.example.module
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class CityRestTest {
    @Test
    fun testCreateCity() = testApplication {
        application {
            module()
        }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val response = client.post("/cities") {
            header(
                HttpHeaders.ContentType,
                ContentType.Application.Json
            )

            setBody(
                CityModel(name = "Cidade", id = null, population = 3322)
            )
        }

        assertEquals(HttpStatusCode.Created, response.status)
        assertEquals(1, response.body())
    }
}