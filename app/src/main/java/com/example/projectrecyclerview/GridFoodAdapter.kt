package com.example.projectrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GridFoodAdapter(val listFood: ArrayList<Food>): RecyclerView.Adapter<GridFoodAdapter.GridViewHolder>(){

    private lateinit var onItemCLickCallBack: OnItemCLickCallBack

    fun setOnItemCLickCallBack(onItemClickCallBack: OnItemCLickCallBack) {
        this.onItemCLickCallBack = onItemClickCallBack
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GridFoodAdapter.GridViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_grid_food, viewGroup, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridFoodAdapter.GridViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(listFood[position].photo)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)

        holder.itemView.setOnClickListener {
            onItemCLickCallBack.onItemCLicked(listFood[holder.adapterPosition])
        }
    }

    interface OnItemCLickCallBack {
        fun onItemCLicked(food: Food)
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }
}