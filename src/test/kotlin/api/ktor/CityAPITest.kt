package com.example.api.ktor

import com.example.module
import com.example.presentation.dto.CityCreateBodyDTO
import com.example.testutils.api.ktor.bodyAsJsonPathDoc
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class CityAPITest {
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

            parameter("name", "test")

            setBody(
                CityCreateBodyDTO(name = "Cidade", population = 3322)
            )
        }


        val jsonDoc = response.bodyAsJsonPathDoc()
        val actualId = jsonDoc.read<Int>("$.data.id")
        val expectedId = 1

        assertEquals(HttpStatusCode.Created, response.status)
        assertEquals(expectedId, actualId)
    }
}