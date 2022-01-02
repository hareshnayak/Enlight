package com.hareshnayak.enlight.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hareshnayak.enlight.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article):Long

    @Query("SELECT * FROM  articles")
    fun getAllAtricle():LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}