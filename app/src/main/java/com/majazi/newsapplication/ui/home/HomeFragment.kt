package com.majazi.newsapplication.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.majazi.newsapplication.R
import com.majazi.newsapplication.databinding.FragmentHomeBinding
import com.majazi.newsapplication.databinding.ItemHomeBinding
import com.majazi.newsapplication.ui.adapter.HomeAdapter
import com.majazi.newsapplication.ui.adapter.SpannedGridLayoutManager

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpannedGridLayout()
        val adapter=HomeAdapter()
        binding.recyHome.adapter =adapter
    }


    private fun setupSpannedGridLayout() {
        val manager = SpannedGridLayoutManager(
            object : SpannedGridLayoutManager.GridSpanLookup {
                override fun getSpanInfo(position: Int): SpannedGridLayoutManager.SpanInfo {
                    return if (position % 12 == 0 || position % 12 == 7) {
                        SpannedGridLayoutManager.SpanInfo(2, 2)
                    } else {
                        SpannedGridLayoutManager.SpanInfo(1, 1)
                    }
                }
            },
            3,
            1f
        )
        binding.recyHome.layoutManager =manager
    }


}