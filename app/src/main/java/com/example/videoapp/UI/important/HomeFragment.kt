package com.example.videoapp.UI.important

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.videoapp.adapters.RecAdapter
import com.example.videoapp.databinding.FragmentHomeBinding
import com.example.videoapp.models.VideoModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: RecAdapter

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
                "https://v16-webapp.tiktok.com/ad763f7340d93b67ac0aabf8e5160261/629e2373/video/tos/alisg/tos-alisg-pve-0037c001/552bcf8ede0d4d7b9f878b09f22b2817/?a=1988&ch=0&cr=0&dr=0&lr=tiktok_m&cd=0%7C0%7C1%7C0&cv=1&br=652&bt=326&btag=80000&cs=0&ds=3&ft=eXd.6HmiMyq8Zoeviwe2N5yQml7Gb&mime_type=video_mp4&qs=0&rc=Ozg1M2k7Zmk1ZTM1M2hoN0Bpajw1bzk6Zm1qZDMzODczNEBiNS4uL2EtNWMxXjFjM2IyYSMwNWVgcjRfMGJgLS1kMS1zcw%3D%3D&l=20220606095525010244087085201BA1E1",
                "1",

                ),
            VideoModel(
                "https://v16-webapp.tiktok.com/f44555c60bdd961bdd980ca788fe57f4/629e245f/video/tos/useast2a/tos-useast2a-pve-0068/83b2b81125294522b772a97b4c663c4a/?a=1988&ch=0&cr=0&dr=0&lr=tiktok_m&cd=0%7C0%7C1%7C0&cv=1&br=1758&bt=879&btag=80000&cs=0&ds=3&ft=eXd.6HmiMyq8ZHCviwe2NqgUml7Gb&mime_type=video_mp4&qs=0&rc=NzVnNWk8Ozg2ZjYzOjk1PEBpMzM3OzQ6Znc4NjMzNzczM0AyYGFjNjBjNmIxLmIvNTI0YSNfM3BncjRnZHBgLS1kMTZzcw%3D%3D&l=20220606095912010245010181121BD2AE",
                "2",

                ),
            VideoModel(
                "https://v16-webapp.tiktok.com/a9db9ffcc7d66053af907f269f9a3672/629e248b/video/tos/useast2a/tos-useast2a-pve-0068/855e649b986844be837a155596cb0dad/?a=1988&ch=0&cr=0&dr=0&lr=tiktok_m&cd=0%7C0%7C1%7C0&cv=1&br=3306&bt=1653&btag=80000&cs=0&ds=3&ft=eXd.6HmiMyq8ZWvZiwe2Nl2hml7Gb&mime_type=video_mp4&qs=0&rc=MztnZWQ2ZmY3Z2VnZ2c8Z0BpajdzNGQ6ZjV2OjMzNzczM0BgMTM0L14xX2IxLjU2X18zYSNgNV4ycjQwYHNgLS1kMTZzcw%3D%3D&l=20220606095947010245242169151B45E8",
                "3",

                ),
            VideoModel(
                "https://v16-webapp.tiktok.com/678f24e16d126d9f003bfb32b6833899/629e2483/video/tos/maliva/tos-maliva-ve-0068c799-us/9c9b752597594d4ebadcb13266f3b16a/?a=1988&ch=0&cr=0&dr=0&lr=tiktok_m&cd=0%7C0%7C1%7C0&cv=1&br=4110&bt=2055&btag=80000&cs=0&ds=3&ft=eXd.6HmiMyq8ZWvZiwe2Nl2hml7Gb&mime_type=video_mp4&qs=0&rc=PGk1OzgzZmU4OTY6Mzg4O0BpamxudDc6Zm5lOjMzZzczNEAvYWEwL2BhXmIxMTYvLjYuYSNkLl9jcjRnaWpgLS1kMS9zcw%3D%3D&l=20220606095947010245242169151B45E8",
                "4",

                ),
            VideoModel(
                "https://v16-webapp.tiktok.com/10004a234aea73bba2a67f564fc6c885/629e24bb/video/tos/useast2a/tos-useast2a-pve-0037c001-aiso/be8b362bb93e439ea06875a7144f3d09/?a=1988&ch=0&cr=0&dr=0&lr=tiktok&cd=0%7C0%7C1%7C0&cv=1&br=3796&bt=1898&btag=80000&cs=0&ds=3&ft=eXd.6HmiMyq8ZurZiwe2NR_nml7Gb&mime_type=video_mp4&qs=0&rc=MzVlaTgzOTw3aWc3PGVoaUBpMzt3eTk6ZnBxOTMzZjgzM0BhL2FgYTBjXjExXy0uLTVfYSMuaWNucjRfL2hgLS1kL2Nzcw%3D%3D&l=20220606100036010245019032121CD687",
                "5",

                ),
            VideoModel(
                "https://v16-webapp.tiktok.com/94efbb5f65180a7d0f35a8bdd7b0e9e8/629e24bc/video/tos/useast2a/tos-useast2a-ve-0068c004/39862e772cb44647beeec3ef3b51a2bb/?a=1988&ch=0&cr=0&dr=0&lr=tiktok_m&cd=0%7C0%7C1%7C0&cv=1&br=3598&bt=1799&btag=80000&cs=0&ds=3&ft=eXd.6HmiMyq8ZurZiwe2NR_nml7Gb&mime_type=video_mp4&qs=0&rc=PGZoNWg5ZTgzZ2Y6OmQ4aUBpM2ZmNjo6Zm1mOzMzNzczM0A1M2FfLWM2NWAxNi5hLzQuYSNxcWNecjRvY25gLS1kMTZzcw%3D%3D&l=20220606100036010245019032121CD687",
                "6",

                ),
            VideoModel(
                "https://v16-webapp.tiktok.com/06445f6fc4a7375ff6c6ded63a5a498b/629e24f4/video/tos/useast2a/tos-useast2a-ve-0068c004/c45a363fc6c148d5a71151a04801fc4a/?a=1988&ch=0&cr=0&dr=0&lr=tiktok_m&cd=0%7C0%7C1%7C0&cv=1&br=1816&bt=908&btag=80000&cs=0&ds=3&ft=eXd.6HmiMyq8ZSrZiwe2NQfnml7Gb&mime_type=video_mp4&qs=0&rc=Njw3Nzo5MzczZWZkZGdmZ0BpMzlnOmg6Zjg7OjMzNzczM0AvYjExXy41NmMxMDUuXmEyYSNxMWdgcjRnMWJgLS1kMTZzcw%3D%3D&l=20220606100126010245015146191BED9A",
                "7",

                ),
            VideoModel(
                "https://v16-webapp.tiktok.com/963eb4371ed65e10f9ba0fa3eedcb3e2/629e2516/video/tos/useast2a/tos-useast2a-pve-0068/b4f0af17630b401dba210613c755b580/?a=1988&ch=0&cr=0&dr=0&lr=tiktok_m&cd=0%7C0%7C1%7C0&cv=1&br=2574&bt=1287&btag=80000&cs=0&ds=3&ft=eXd.6HmiMyq8ZcmZiwe2NfPhml7Gb&mime_type=video_mp4&qs=0&rc=aWk2NmY1ZTc8ZGk4OWQ7M0BpMzllNjg6ZjprNzMzNzczM0A0LzYyNi9gNV4xMDEzMDNeYSNrNGprcjRfb29gLS1kMTZzcw%3D%3D&l=20220606100214010251065124201CFCA2",
                "8",

                ),
            VideoModel(
                "https://v16-webapp.tiktok.com/67df36bda7db48ac0382d03387e420a6/629e2537/video/tos/maliva/tos-maliva-ve-0068c799-us/b895614cd4ad4c1d9ff97477b64f0f14/?a=1988&ch=0&cr=0&dr=0&lr=tiktok_m&cd=0%7C0%7C1%7C0&cv=1&br=4178&bt=2089&btag=80000&cs=0&ds=3&ft=eXd.6HmiMyq8ZfgZiwe2N_5Qml7Gb&mime_type=video_mp4&qs=0&rc=OzdlNTc2ZjY0Z2U5Mzg0ZUBpM2tsajQ6Zjx5OTMzZzczNEA0Li5hYi9iNmAxNTYyMTBeYSM2LWNfcjRnM2xgLS1kMS9zcw%3D%3D&l=20220606100249010251077132241CDB32",
                "9",

                ),
            VideoModel(
                "https://v16-webapp.tiktok.com/bcae78708fb06eca0d6357e9d1e23d62/629e256a/video/tos/maliva/tos-maliva-ve-0068c799-us/0522dc36486c476eba65de36ac444209/?a=1988&ch=0&cr=0&dr=0&lr=tiktok_m&cd=0%7C0%7C1%7C0&cv=1&br=4244&bt=2122&btag=80000&cs=0&ds=3&ft=eXd.6HmiMyq8ZpgZiwe2NGbnml7Gb&mime_type=video_mp4&qs=0&rc=NWdkOmU8OjxlOWZnO2Q2ZkBpM2c4czc6ZnFzPDMzZzczNEAwYC0vMC1fNTYxMS5jYjJiYSNrbWBrcjRnb21gLS1kMS9zcw%3D%3D&l=20220606100338010245155110221CAA14",
                "10",

                ),

            )

        db.collection("videos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    videoList += (VideoModel(
                        document.get("videoId").toString(),
                        document.get("videoURL").toString(),
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
        val snapHelper = LinearSnapHelper() // Or PagerSnapHelper
        snapHelper.attachToRecyclerView(binding.recView)
    }
}
