package com.example.hdev.retrofitsample.data.models

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("name") var name: String,
    @SerializedName("email") var email: String,
    @SerializedName("phone") var phone: String
)

class Users(
    @SerializedName("employeeList")
    var usersData: List<User>? = null
)
