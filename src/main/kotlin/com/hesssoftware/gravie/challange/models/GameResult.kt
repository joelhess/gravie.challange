package com.hesssoftware.gravie.challange.models

data class GameResult(
    val id: String,
    val name: String,
    val description: String?,
    val image: HashMap<String, String>,
)