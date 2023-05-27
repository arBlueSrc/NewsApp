package com.majazi.newsapplication.peresentation.ui.savedNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.majazi.newsapplication.MainActivity
import com.majazi.newsapplication.R
import com.majazi.newsapplication.databinding.FragmentSavedNewsBinding
import com.majazi.newsapplication.peresentation.adapter.SavedNewsAdapter
import com.majazi.newsapplication.peresentation.viewmodel.newslist.NewListViewModel


class SavedNewsFragment : Fragment() {
    private lateinit var binding: FragmentSavedNewsBinding
    private lateinit var savedNewsAdapter: SavedNewsAdapter
    private lateinit var viewModel: NewListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_saved_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).newsListViewModel
        savedNewsAdapter = (activity as MainActivity).savedNewsAdapter
        viewNewsList()
    }


    private fun viewNewsList() {
        viewModel.getSavedNews().observe(viewLifecycleOwner) { response ->
            binding.recySavedNews.adapter = savedNewsAdapter
            savedNewsAdapter.differ.submitList(response)
        }
    }

}