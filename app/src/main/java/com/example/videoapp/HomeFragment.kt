package com.example.videoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.videoapp.databinding.FragmentGeneralBinding
import com.example.videoapp.databinding.FragmentHomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: RecAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("LogNotTimber")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore

        val videoList = mutableListOf(
            VideoModel(
                "1",
                "football",
                "https://v16-webapp.tiktok.com/432e7799002ce5c8c857e8b91d265c6a/628cf115/video/tos/alisg/tos-alisg-pve-0037c001/1dbfccceb3b84df18c4f997552063081/?a=1988&ch=0&cr=0&dr=0&lr=tiktok_m&cd=0%7C0%7C1%7C0&cv=1&br=2302&bt=1151&cs=0&ds=3&ft=eXd.6HmiMyq8Z1JsTwe2NToQml7Gb&mime_type=video_mp4&qs=0&rc=Njg3OTZmZDU3Z2g1ZDM8aEBpM3dzazQ6ZnR4OzMzODczNEBfLzNiXy4zXjYxLl80LzQvYSNtbS9ocjRfNWVgLS1kMS1zcw%3D%3D&l=2022052408514801024500205422073C87"
            ),
            VideoModel(
                "2",
                "football",
                "https://v16-webapp.tiktok.com/a777f3a10b1e2a574e248ef99ca37f15/628cf117/video/tos/maliva/tos-maliva-ve-0068c799-us/0559399551f745bebf128d5c177b5667/?a=1988&ch=0&cr=0&dr=0&lr=tiktok&cd=0%7C0%7C1%7C0&cv=1&br=3772&bt=1886&cs=0&ds=3&ft=eXd.6HmiMyq8Z1JsTwe2NToQml7Gb&mime_type=video_mp4&qs=0&rc=OWVpN2Q0ZzdpNTo5NzRkOEBpanJvbTM6ZnlzPDMzZzczNEAvMjRfLTMyXjQxNi5fYDUtYSNqamRicjRfaS1gLS1kMS9zcw%3D%3D&l=2022052408514801024500205422073C87"
            ),
            VideoModel(
                "3",
                "dance",
                "https://v16-webapp.tiktok.com/f4f80c8bce0e9cc7ba5820ba363bcb48/628cf15c/video/tos/useast2a/tos-useast2a-ve-0068c004/03597711cf8c4b83aaf6a1aa22fb7495/?a=1988&ch=0&cr=0&dr=0&lr=tiktok_m&cd=0%7C0%7C1%7C0&cv=1&br=2704&bt=1352&cs=0&ds=3&ft=eXd.6HmiMyq8Z~psTwe2NDQBml7Gb&mime_type=video_mp4&qs=0&rc=ODo5aTs5NDlpN2ZnZWloZUBpanFzajU6ZmZoOjMzNzczM0BiNS4uLWM0NS8xM14vXzZiYSNsLjMwcjQwc3FgLS1kMTZzcw%3D%3D&l=2022052408525101024402622924071F1A"
            ),
            VideoModel(
                "4",
                "dance",
                "https://v16-webapp.tiktok.com/19fb6a9f6bbb329b1ae452a19b730a33/628cf154/video/tos/useast2a/tos-useast2a-ve-0068c003/13bbb2c7033b4870a08e1fe7e16cd2b7/?a=1988&ch=0&cr=0&dr=0&lr=tiktok_m&cd=0%7C0%7C1%7C0&cv=1&br=4642&bt=2321&cs=0&ds=3&ft=eXd.6HmiMyq8Z~psTwe2NDQBml7Gb&mime_type=video_mp4&qs=0&rc=aTo7aDw7OmhkaDwzZzk8M0BpM3FtZmQ6Zjd3OjMzNzczM0A1YDJfMWEwNTExNWI1YTVeYSNgazZycjRvMnNgLS1kMTZzcw%3D%3D&l=2022052408525101024402622924071F1A"
            ),
            VideoModel(
                "5",
                "humor",
                "https://v16-webapp.tiktok.com/905748284c69bce7a4b1e35fe3ea3ef1/628cf253/video/tos/useast2a/tos-useast2a-ve-0068c004/9d5b2e46a73f4076b6c1a24a7c16c0d3/?a=1988&ch=0&cr=0&dr=0&lr=tiktok_m&cd=0%7C0%7C1%7C0&cv=1&br=3422&bt=1711&cs=0&ds=3&ft=eXd.6HmiMyq8ZJYsTwe2NOKUml7Gb&mime_type=video_mp4&qs=0&rc=MzdkZzc4NjQzZDs2NTQzZkBpM25zazk6ZmlmOjMzNzczM0BhYDUtLl8yX2ExMjNjLi8tYSNzcDIzcjRfNGJgLS1kMTZzcw%3D%3D&l=2022052408571301024524417808081320"
            )

        )

        db.collection("videos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    videoList += (VideoModel(
                        document.get("videoId").toString(),
                        document.get("videoURL").toString(),
                        document.get("videoType").toString(),
                    ))
                    Log.d("TAG", "${document.id} => ${document.data}")
                }

            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }

        adapter = context?.let { RecAdapter(videoList, it) }!!
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
        binding.recView.isVerticalScrollBarEnabled
        val snapHelper = LinearSnapHelper() // Or PagerSnapHelper
        snapHelper.attachToRecyclerView(binding.recView)

    }
}