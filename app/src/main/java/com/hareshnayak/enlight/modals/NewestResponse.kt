package com.hareshnayak.enlight.modals

data class NewestResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)