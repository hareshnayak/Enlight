package com.hareshnayak.enlight

data class NewestResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)