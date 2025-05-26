package com.example.api.ktor

import com.example.module
import com.example.presentation.dto.CityCreateBodyDTO
import com.example.presentation.dto.CityCreateResponseDataDTO
import com.example.presentation.dto.HTTPDataResponseObject
import io.ktor.client.call.*
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

            setBody(
                CityCreateBodyDTO(name = "Cidade", population = 3322)
            )
        }

        assertEquals(HttpStatusCode.Created, response.status)
        val expect = HTTPDataResponseObject(
            CityCreateResponseDataDTO(
                id = 1
            )
        )
        val actual = response.body<HTTPDataResponseObject<CityCreateResponseDataDTO>>().data.id

        assertEquals(expect.data.id, actual)
    }
}