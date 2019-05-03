package com.example.hdev.retrofitsample.data.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hdev.retrofitsample.data.models.User

class UserViewModel : ViewModel() {

    val user: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
}
}
