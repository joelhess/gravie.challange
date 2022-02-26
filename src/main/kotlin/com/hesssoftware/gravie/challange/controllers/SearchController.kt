package com.hesssoftware.gravie.challange.controllers

import com.hesssoftware.gravie.challange.providers.SearchProvider
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class SearchController(val searchProvider: SearchProvider) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @RequestMapping("/search")
    fun search(model: Model, @RequestParam searchString: String = ""): String {
        logger.info("Received search request.")
        if (searchString.isEmpty()) {
            model.addAttribute("results", null)
        } else {
            val results = searchProvider.search(searchString)
            model.addAttribute("results", results)
        }
        return "index"
    }
}
