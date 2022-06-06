package com.example.videoapp.UI.important

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.videoapp.adapters.PhotosAdapter
import com.example.videoapp.databinding.FragmentPhotosBinding
import com.example.videoapp.models.PhotosModel

class PhotosFragment : Fragment() {
    private lateinit var binding: FragmentPhotosBinding
    lateinit var adapter: PhotosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPhotosBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photoList = mutableListOf(
            PhotosModel(
                "https://media.istockphoto.com/photos/beautiful-sunset-over-the-tropical-sea-picture-id1172427455?k=20&m=1172427455&s=612x612&w=0&h=tL1ig4N68zXv9wKgZ3_tOeVP1qV3zSfjjS_jbyeyGCA="
            ),
            PhotosModel(
                "https://media.gettyimages.com/photos/armenia-yerevan-republic-square-dancing-fountains-picture-id1068746262?s=612x612"
            ),
            PhotosModel(
                "https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80"
            ),
            PhotosModel(
                "https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?cs=srgb&dl=pexels-anjana-c-674010.jpg&fm=jpg"
            ),
            PhotosModel(
                "https://images.pexels.com/photos/302743/pexels-photo-302743.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
            ),
            PhotosModel(
                "https://www.gettyimages.ae/gi-resources/images/500px/983794168.jpg"
            ),
            PhotosModel(
                "https://st.depositphotos.com/1006706/2671/i/600/depositphotos_26715369-stock-photo-which-way-to-choose-3d.jpg"
            ),
            PhotosModel(
                "https://love-shayari.co/wp-content/uploads/2021/10/sun-rise.jpg"
            ),
            PhotosModel(
                "https://i0.wp.com/quoteshindi.net/wp-content/uploads/good-night-images.jpg?fit=700%2C750&ssl=1"
            )
        )
        adapter = context?.let { PhotosAdapter(photoList, it) }!!
        binding.photosRec.layoutManager = LinearLayoutManager(context)
        binding.photosRec.adapter = adapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.photosRec)
    }
}

