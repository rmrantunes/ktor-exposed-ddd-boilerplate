package com.example.domain.service

import com.example.domain.model.CityModel
import com.example.testutils.repository.FakeCityRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class CityServiceTest {
    @Test
    fun test() = runBlocking {
        val repository = FakeCityRepository()
        val service = CityService(repository)
        val result = service.create(CityModel(
            id = null,
            name = "Cacoal",
            population = 1000
        ))
        val result2 = service.create(CityModel(
            id = null,
            name = "Memphis",
            population = 3000
        ))

        assertEquals(1, result)
        assertEquals(2, result2)
    }
}