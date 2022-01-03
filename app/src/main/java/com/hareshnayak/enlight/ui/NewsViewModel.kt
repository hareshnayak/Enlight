package com.hareshnayak.enlight.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hareshnayak.enlight.modals.Article
import com.hareshnayak.enlight.modals.NewestResponse
import com.hareshnayak.enlight.repository.NewsRepository
import com.hareshnayak.enlight.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewestResponse>>  = MutableLiveData()
    var breakingNewsPage : Int = 1
    var breakingNewsResponse : NewestResponse? = null
    val searchNews: MutableLiveData<Resource<NewestResponse>>  = MutableLiveData()
    var searchNewsPage : Int = 1
    var searchNewsResponse : NewestResponse? = null

    init{
        getBreakingNews("in")
    }

    fun getBreakingNews(countryCode:String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response : Response<NewestResponse> = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun searchNews(searchQuery:String) = viewModelScope.launch{
        searchNews.postValue(Resource.Loading())
        val response : Response<NewestResponse> = newsRepository.searchNews(searchQuery,searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewestResponse>):Resource<NewestResponse>{
        if(response.isSuccessful){
            response.body()?.let{ resultResponse ->
                breakingNewsPage++
                if(breakingNewsResponse == null){
                    breakingNewsResponse = resultResponse
                }else{
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(breakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewestResponse>):Resource<NewestResponse>{
        if(response.isSuccessful){
            response.body()?.let{ resultResponse ->
                searchNewsPage++
                if(searchNewsResponse == null){
                    searchNewsResponse = resultResponse
                }else{
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }
    fun getSavedNews() = newsRepository.getSavedNews()
    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }
}
