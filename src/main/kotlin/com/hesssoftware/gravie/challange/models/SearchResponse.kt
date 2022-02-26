package com.hesssoftware.gravie.challange.models

data class SearchResponse (
    val error: String,
    val limit: Int,
    val offset: Int,
    val number_of_page_results: Int,
    val number_of_total_results: Int,
    val results: List<GameResult>
)