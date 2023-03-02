package com.example.projectrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListFoodAdapter(private val listFood: ArrayList<Food>): RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {
    private lateinit var onItemCLickCallBack: OnItemCLickCallBack
    fun setOnItemClickCallBack(onItemCLickCallBack: OnItemCLickCallBack) {
        this.onItemCLickCallBack = onItemCLickCallBack
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, from, photo) = listFood[position]

        Glide.with(holder.itemView.context)
            .load(photo)
            .apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)

        holder.tvName.text = name
        holder.tvFrom.text = from

        holder.itemView.setOnClickListener{
            onItemCLickCallBack.onItemClicked(listFood[holder.adapterPosition])
        }
    }

    interface OnItemCLickCallBack {
        fun onItemClicked(data: Food)
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_food, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvFrom: TextView = itemView.findViewById(R.id.tv_item_from)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }
}