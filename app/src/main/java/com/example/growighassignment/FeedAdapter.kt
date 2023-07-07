package com.example.growighassignment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.growighassignment.databinding.FeedItemBinding
import com.squareup.picasso.Picasso

class FeedAdapter(private var userList: List<ImageData>) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    private val limit = 10

    inner class FeedViewHolder(itemBinding: FeedItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var rvImage = itemBinding.rvImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(
            FeedItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (userList.size > limit) {
            limit
        } else userList.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        Picasso.get().load(userList[position].avatar_url).into(holder.rvImage)
    }
}