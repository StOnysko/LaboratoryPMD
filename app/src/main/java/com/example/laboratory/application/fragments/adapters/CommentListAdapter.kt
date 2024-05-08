package com.example.laboratory.application.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratory.application.data.Comment
import com.example.laboratory.databinding.CommentLayoutBinding

class CommentListAdapter :
    ListAdapter<Comment, CommentListAdapter.CommentItemViewHolder>(CommentDiffUtil()) {
    class CommentItemViewHolder(private val binding: CommentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comments: Comment) {
            binding.apply {
                commentContent.text = comments.postText
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CommentLayoutBinding.inflate(inflater, parent, false)
        return CommentItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentItemViewHolder, position: Int) {
        val comment = getItem(position)
        holder.bind(comment)
    }

    class CommentDiffUtil : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.id == newItem.id
        }
    }
}