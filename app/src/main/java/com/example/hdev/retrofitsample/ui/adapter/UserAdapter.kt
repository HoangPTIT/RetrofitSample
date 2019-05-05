package com.example.hdev.retrofitsample.ui.adapter

import android.os.Build.VERSION_CODES
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hdev.retrofitsample.R
import com.example.hdev.retrofitsample.data.models.User
import com.example.hdev.retrofitsample.databinding.ItemUserBinding
import java.util.Objects

class UserAdapter(var users: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    fun updateUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    fun refreshUsers(users: List<User>) = when {
        this.users.isNullOrEmpty() -> {
            this.users = users
            notifyItemRangeInserted(0, users.size)
        }
        else -> {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return this@UserAdapter.users.get(oldItemPosition).name == users.get(newItemPosition).name
                }

                override fun getOldListSize(): Int {
                    return this@UserAdapter.users.size
                }

                override fun getNewListSize(): Int {
                    return users.size
                }

                @RequiresApi(VERSION_CODES.KITKAT)
                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val user = users.get(newItemPosition)
                    val old = users.get(oldItemPosition)
                    return user.name == old.name && Objects.equals(user.email, old.email)
                }
            })
            this.users = users
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: ItemUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (users.isNullOrEmpty()) 0 else users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user
            binding.executePendingBindings()
        }
    }
}
