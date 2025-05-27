package com.example.presentation.api.ktor

import com.example.module
import com.example.presentation.api.dto.CityCreateBodyDTO
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
    fun testShouldCreateCity() = testApplication {
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
                CityCreateBodyDTO(name = "Cidade", population = 3322)
            )
        }


        val jsonDoc = response.bodyAsJsonPathDoc()
        val actualId = jsonDoc.read<Int>("$.data.id")
        val expectedId = 1

        assertEquals(HttpStatusCode.Created, response.status)
        assertEquals(expectedId, actualId)
    }

    @Test
    fun testShouldGetCity() = testApplication {
        application {
            module()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        client.post("/cities") {
            header(
                HttpHeaders.ContentType,
                ContentType.Application.Json
            )

            setBody(
                CityCreateBodyDTO(name = "Cidade", population = 3322)
            )
        }

        val response2 = client.get("/cities/1")

        val jsonDoc = response2.bodyAsJsonPathDoc()
        val actualId = jsonDoc.read<Int>("$.data.id")
        val actualName = jsonDoc.read<String>("$.data.name")
        val actualPopulation = jsonDoc.read<Int>("$.data.population")

        assertEquals("Cidade", actualName)
        assertEquals(3322, actualPopulation)
        assertEquals(1, actualId)
    }

    @Test
    fun testShouldGetCityReturnNotFound() = testApplication {
        application {
            module()
        }

        val response = client.get("/cities/1233")
        val jsonDoc = response.bodyAsJsonPathDoc()
        val errors: List<String> = jsonDoc.read("$.errors")

        assertEquals(HttpStatusCode.NotFound, response.status)
        assertEquals("City not found", errors[0])
    }
}