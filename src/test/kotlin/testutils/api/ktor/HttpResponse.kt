package com.example.testutils.api.ktor

import com.jayway.jsonpath.DocumentContext
import com.jayway.jsonpath.JsonPath
import io.ktor.client.statement.*

suspend fun HttpResponse.bodyAsJsonPathDoc(): DocumentContext {
    return JsonPath.parse(this.bodyAsText())
}