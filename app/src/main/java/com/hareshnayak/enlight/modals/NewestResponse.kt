package com.hareshnayak.enlight.modals

data class NewestResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)