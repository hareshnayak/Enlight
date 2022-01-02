package com.hareshnayak.enlight.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.hareshnayak.enlight.R
import com.hareshnayak.enlight.ui.MainActivity
import com.hareshnayak.enlight.ui.NewsViewModel

class BreakingNews : Fragment(R.layout.fragment_breaking_news) {
    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }
}