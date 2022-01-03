package com.hareshnayak.enlight.repository

import com.hareshnayak.enlight.api.RetrofitInstance
import com.hareshnayak.enlight.db.ArticleDatabase
import com.hareshnayak.enlight.modals.Article

class NewsRepository(val db:ArticleDatabase) {
    suspend fun getBreakingNews(countryCode: String, pageNumber:Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery:String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticle()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}