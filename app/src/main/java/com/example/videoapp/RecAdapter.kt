package com.example.videoapp

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.example.videoapp.databinding.VideoItemBinding


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
        var binding = VideoItemBinding.bind(itemView)
        var clickLike = false

        fun bind(videoModel: VideoModel) {
            val mediaController = MediaController(context)

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
                } else if (clickLike == false) {
                    clickLike = true
                    binding.like.setImageResource(R.drawable.icon_like)
                }
            }
        }
    }
}
