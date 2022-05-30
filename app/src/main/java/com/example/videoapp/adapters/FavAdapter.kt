package com.example.videoapp.adapters

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.example.videoapp.R
import com.example.videoapp.VideoModel
import com.example.videoapp.databinding.VideoItemBinding
import com.example.videoapp.objects.Global
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FavAdapter(
    private val videoModelLIst: MutableList<VideoModel>, val context: Context
) : RecyclerView.Adapter<FavAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false)
        //   getVideo()
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavAdapter.VideoViewHolder, position: Int) {
        val tempVideos = videoModelLIst[position]
        holder.bind2(tempVideos, context)
    }

    override fun getItemCount(): Int = videoModelLIst.size

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var firebaseAuth: FirebaseAuth

        private var binding = VideoItemBinding.bind(itemView)
        private var clickLike = false

        fun bind2(videoModel: VideoModel, context: Context) {
            firebaseAuth = FirebaseAuth.getInstance()

            val mediaController = MediaController(context)

            mediaController.setAnchorView(binding.videoView)
            binding.videoView.setMediaController(mediaController)
            binding.videoView.setVideoURI(Uri.parse(videoModel.videoURL))
            binding.videoView.requestFocus()
            binding.videoView.start()

            clickLike = true
            binding.like.setOnClickListener {

                if (clickLike) {
                    clickLike = false
                    binding.like.setImageResource(R.drawable.icon_redlike)
                } else if (!clickLike) {
                    clickLike = true
                    binding.like.setImageResource(R.drawable.icon_like)
                }
            }
        }
    }
}