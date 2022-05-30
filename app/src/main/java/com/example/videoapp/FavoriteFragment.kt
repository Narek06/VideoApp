package com.example.videoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import com.example.videoapp.objects.Global
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var adapter: FavAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater)
        getVideo()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  val videoList = mutableListOf<VideoModel>()
        adapter = context?.let { FavAdapter(Global.list, it) }!!
        binding.favRec.layoutManager = LinearLayoutManager(context)
        binding.favRec.adapter = adapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.favRec)
    }

    fun getVideo() {
        val ref = FirebaseDatabase.getInstance().getReference("Url")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // var list = ArrayList<VideoModel>()

                for (data in snapshot.children) {
                    val model = data.getValue(VideoModel::class.java)
                    Global.list.add(model as VideoModel)
                }
            }

            @SuppressLint("LogNotTimber")
            override fun onCancelled(error: DatabaseError) {
                Log.e("cancel", error.toString())
            }
        })
    }
}