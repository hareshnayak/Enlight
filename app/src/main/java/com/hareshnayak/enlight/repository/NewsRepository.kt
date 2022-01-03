package com.hareshnayak.enlight.repository

import com.hareshnayak.enlight.api.RetrofitInstance
import com.hareshnayak.enlight.db.ArticleDatabase

class NewsRepository(val db:ArticleDatabase) {
    suspend fun getBreakingNews(countryCode: String, pageNumber:Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery:String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)
}