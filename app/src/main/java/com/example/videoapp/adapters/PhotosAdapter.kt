package com.example.videoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.videoapp.R
import com.example.videoapp.databinding.PhotosItemBinding
import com.example.videoapp.models.PhotosModel
import com.squareup.picasso.Picasso

class PhotosAdapter(
    private val photosModelLIst: MutableList<PhotosModel>,
    val context: Context
) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotosAdapter.ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.photos_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tempPhotos = photosModelLIst[position]
        Picasso.get().load(tempPhotos.imageUrl)
            .into(holder.image)
    }

    override fun getItemCount(): Int = photosModelLIst.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding = PhotosItemBinding.bind(itemView)

        val image = binding.photosImage
    }
}