package com.hesssoftware.gravie.challange.providers

import com.hesssoftware.gravie.challange.models.SearchResponse

interface SearchProvider {
    fun search(searchString: String): SearchResponse?
}