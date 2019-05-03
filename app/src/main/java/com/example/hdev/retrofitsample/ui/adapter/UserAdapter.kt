package com.example.hdev.retrofitsample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.hdev.retrofitsample.data.models.User
import com.example.hdev.retrofitsample.data.viewmodels.UserViewModel
import com.example.hdev.retrofitsample.databinding.ItemUserBinding

class UserAdapter(@NonNull private var users: List<User>, @NonNull private val userViewModel: UserViewModel) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserViewHolder(ItemUserBinding.inflate(layoutInflater))
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        userViewModel.user.value = users[position]
        holder.bind(userViewModel)
    }

    inner class UserViewHolder(private val itemUserBinding: ItemUserBinding) : RecyclerView.ViewHolder(itemUserBinding.root) {
        fun bind(userViewModel: UserViewModel) {
            itemUserBinding.itemModel = userViewModel
            itemUserBinding.executePendingBindings()
        }
    }
}
