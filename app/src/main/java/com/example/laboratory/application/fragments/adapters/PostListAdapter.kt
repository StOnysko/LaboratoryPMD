package com.example.laboratory.application.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.laboratory.application.data.Post
import com.example.laboratory.application.interfaces.OnClickListener
import com.example.laboratory.databinding.PostItemListBinding

class PostListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Post, PostListAdapter.PostViewHolder>(PostItemDiffUtil()) {
    class PostViewHolder(private val binding: PostItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post, onClickListener: OnClickListener) {
            binding.postDescription.text = post.postDescription
            binding.postHeader.text = post.postHeader
            binding.postImage.load(post.postImage)
            itemView.setOnClickListener { onClickListener.onItemClick(post) }
        }
    }

    class PostItemDiffUtil : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostItemListBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post, onClickListener)
    }
}