package com.example.videoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.videoapp.adapters.FavAdapter
import com.example.videoapp.adapters.RecAdapter
import com.example.videoapp.databinding.FragmentEntryBinding
import com.example.videoapp.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var adapter: FavAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val videoList = mutableListOf<VideoModel>()
        adapter = context?.let { FavAdapter(videoList, it) }!!
        binding.favRec.layoutManager = LinearLayoutManager(context)
        binding.favRec.adapter = adapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.favRec)
    }
}