package com.example.recyclerviewlistsumple

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view) {

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    val nameTextView: TextView = view.findViewById(R.id.nameTextView)
    val contentTextView: TextView = view.findViewById(R.id.contentTextView)
    val itemImageView: ImageView = view.findViewById(R.id.itemImageView)

    init {

    }
}