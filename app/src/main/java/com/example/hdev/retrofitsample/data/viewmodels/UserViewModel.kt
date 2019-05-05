package com.example.hdev.retrofitsample.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hdev.retrofitsample.data.models.Users
import com.example.hdev.retrofitsample.repository.Repository

class UserViewModel : ViewModel() {

    val user: LiveData<Users> by lazy {
        Repository.getInstance().getUsers(100)
    }
}
