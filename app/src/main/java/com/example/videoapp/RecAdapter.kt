//package com.example.videoapp
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//
//class RecAdapter(
//    private val foodModelList: MutableList<FoodModel>, private val onItemClick: (foodModel: FoodModel) -> Unit
//) : RecyclerView.Adapter<RecAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
//        LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
//    )
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val tempFoodModel = foodModelList[position]
//        holder.bind(tempFoodModel)
//    }
//
//    override fun getItemCount(): Int = foodModelList.size
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//    }
//}