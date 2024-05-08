package com.example.laboratory.application.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.laboratory.application.data.User
import com.example.laboratory.databinding.UserListItemBinding

class UsersListAdapter : ListAdapter<User, UsersListAdapter.UserListViewHolder>(ItemDiffUtil()) {
    class UserListViewHolder(private val binding: UserListItemBinding) : ViewHolder(binding.root) {
        fun bind(item: User) {
            binding.userImage.setImageResource(item.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserListItemBinding.inflate(inflater, parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    class ItemDiffUtil : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.image == newItem.image
        }
    }
}