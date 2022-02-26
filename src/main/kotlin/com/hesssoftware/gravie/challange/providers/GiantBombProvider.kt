package com.hesssoftware.gravie.challange.providers

import com.hesssoftware.gravie.challange.models.SearchResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class GiantBombProvider(
    val webClient: WebClient
    ): SearchProvider {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Value("\${giantBomb.apiKey:none}")
    lateinit var apiKey: String

    override fun search(searchString: String): SearchResponse? {
        val result = generateRequest(searchString)
            .bodyToMono<SearchResponse>()
        return result.block()
    }

    private fun generateRequest(searchString: String): WebClient.ResponseSpec {
        logger.info("Generating request for searchString: $searchString")
        val action =  webClient.get()
            .uri { uriBuilder -> uriBuilder
                .path("/search/")
                .queryParam("api_key", apiKey)
                .queryParam("format", "json")
                .queryParam("query", searchString)
                .queryParam("resources", "game")
                .build()
            }
        return action.retrieve()
    }
}

