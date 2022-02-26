package com.hesssoftware.gravie.challange.controllers

import com.hesssoftware.gravie.challange.models.GameResult
import com.hesssoftware.gravie.challange.models.SearchResponse
import com.hesssoftware.gravie.challange.providers.SearchProvider
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.ui.Model

class SearchControllerTests:FreeSpec() {
    init {
        beforeEach {
            clearAllMocks()
        }

        val searchProvider = mockk<SearchProvider>()
        val searchController = SearchController(searchProvider)

        "Results Returned" {
            val model = mockk<Model>(relaxed = true)
            val searchString = "Test"
            val searchResponse = SearchResponse("OK", 10, 0, 2, 15, listOf(GameResult("id", "name", null, HashMap())))

            every { searchProvider.search(any())} returns searchResponse

            val result = searchController.search(model, searchString)

            verify {
                searchProvider.search(searchString)
                model.addAttribute("results", searchResponse)
            }

            result.shouldBe("index")
        }

        "No SearchString returns null results" {
            val model = mockk<Model>(relaxed = true)

            val result = searchController.search(model, "")

            verify(exactly = 0) {
                searchProvider.search(any())
            }
            verify {
                model.addAttribute("results", null)
            }

            result.shouldBe("index")
        }

    }
}