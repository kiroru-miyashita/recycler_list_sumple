package com.example.recyclerviewlistsumple

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

data class Post(val nickname: String, val caption: String)

class RecyclerAdapter(
    private val context: Context,
    private val itemClickListener: RecyclerViewHolder.ItemClickListener,
    private val itemList:MutableList<Post>
    ) : RecyclerView.Adapter<RecyclerViewHolder>() {

    private var mRecyclerView : RecyclerView? = null

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.let {
            it.nameTextView.text = itemList.get(position).nickname
            it.contentTextView.text = itemList.get(position).caption
            it.itemImageView.setImageResource(R.mipmap.ic_launcher)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val myView = layoutInflater.inflate(R.layout.list_item, parent, false)

        myView.setOnClickListener { view ->
            mRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }

        return RecyclerViewHolder(myView)
    }

    fun add(listData: List<Post>) {
        this.itemList += listData
        notifyDataSetChanged()
    }
}