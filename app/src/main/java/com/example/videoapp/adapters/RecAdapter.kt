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
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.videoapp.R
import com.example.videoapp.VideoModel
import com.example.videoapp.databinding.VideoItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


class RecAdapter(
    private val videoModelLIst: MutableList<VideoModel>, val context: Context
) : RecyclerView.Adapter<RecAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tempVideos = videoModelLIst[position]
        holder.bind(tempVideos)

    }

    override fun getItemCount(): Int = videoModelLIst.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var firebaseAuth: FirebaseAuth

        var videoUrl = ""
        var videoType = ""
        var videoId = ""


        private var binding = VideoItemBinding.bind(itemView)
        private var clickLike = false

        fun bind(videoModel: VideoModel) {
            videoUrl = videoModel.videoURL
            videoId = videoModel.videoId
            videoType = videoModel.videoType

            val mediaController = MediaController(context)

            firebaseAuth = FirebaseAuth.getInstance()

            mediaController.setAnchorView(binding.videoView)
            binding.videoView.setMediaController(mediaController)
            binding.videoView.setVideoURI(Uri.parse(videoModel.videoURL))
            binding.videoView.requestFocus()
            binding.videoView.start()
            binding.videoView

            clickLike = true
            binding.like.setOnClickListener {

                if (clickLike) {
                    clickLike = false
                    binding.like.setImageResource(R.drawable.icon_redlike)
                    addToFavorite()
                } else if (!clickLike) {
                    clickLike = true
                    binding.like.setImageResource(R.drawable.icon_like)
                    removeFromFavorite()
                }
            }
        }

        @SuppressLint("LogNotTimber")
        fun addToFavorite() {
            val hashMap = HashMap<String, Any>()
            hashMap["videoUrl"] = videoUrl
            hashMap["videoType"] = videoType
            hashMap["videoId"] = videoId

            val ref = FirebaseDatabase.getInstance().getReference("Users")
            ref.child(firebaseAuth.uid!!).child("Favorites").child(videoId)
                .setValue(hashMap)
                .addOnSuccessListener {
                    Log.d(TAG, "addToFavorite: Added to fav")
                }
                .addOnFailureListener {
                    Toast.makeText(context, "failed", Toast.LENGTH_LONG).show()
                }
        }

        @SuppressLint("LogNotTimber")
        fun removeFromFavorite() {
            Log.d(TAG, "removeFromFavorite: Removing from favorite")

            val ref = FirebaseDatabase.getInstance().getReference("Users")
            ref.child(firebaseAuth.uid!!).child("Favorites").child(videoId)
                .removeValue()
                .addOnSuccessListener {
                    Toast.makeText(context, "Remove from fav", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(context, "failed remove from fav", Toast.LENGTH_LONG).show()
                }
        }
    }
}
