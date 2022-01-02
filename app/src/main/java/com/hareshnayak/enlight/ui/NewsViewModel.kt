package com.hareshnayak.enlight.ui

import androidx.lifecycle.ViewModel
import com.hareshnayak.enlight.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {
}