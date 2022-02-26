package com.hesssoftware.gravie.challange.providers

import com.fasterxml.jackson.databind.ObjectMapper
import com.hesssoftware.gravie.challange.models.GameResult
import com.hesssoftware.gravie.challange.models.SearchResponse
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

import org.springframework.test.context.TestPropertySource
import org.springframework.web.reactive.function.client.WebClient

@TestPropertySource(properties = ["giantBomb.apiKey=45"])
class GiantBombProviderTests : FreeSpec() {
    init {
        val mockWebServer = MockWebServer()

        val giantBombProvider = GiantBombProvider(WebClient.create("http://localhost:${mockWebServer.port}"))
        giantBombProvider.apiKey = "none"

        "Search Should Return Results" {
            val searchResponse = SearchResponse("OK", 10, 0, 2, 15, listOf(GameResult("id", "name", null, HashMap())))
            mockWebServer.enqueue(MockResponse()
                .setBody(ObjectMapper().writeValueAsString(searchResponse))
                .addHeader("Content-Type", "application/json")
            )

            val result = giantBombProvider.search("zelda")

            val request = mockWebServer.takeRequest()

            request.requestUrl!!.queryParameterNames.shouldContainAll("api_key", "format", "query", "resources")
            result.shouldBe(searchResponse)
        }

    }

}