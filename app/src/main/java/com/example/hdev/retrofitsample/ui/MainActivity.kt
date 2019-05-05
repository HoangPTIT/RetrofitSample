package com.example.hdev.retrofitsample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hdev.retrofitsample.R.layout
import com.example.hdev.retrofitsample.data.viewmodels.UserViewModel
import com.example.hdev.retrofitsample.databinding.ActivityMainBinding
import com.example.hdev.retrofitsample.ui.adapter.UserAdapter

class MainActivity : AppCompatActivity() {

    private val userViewModel by lazy { ViewModelProviders.of(this).get(UserViewModel::class.java) }

    private val adapter by lazy { UserAdapter(ArrayList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, layout.activity_main)
        binding.lifecycleOwner = this
        binding.recyclerUsers.layoutManager = LinearLayoutManager(this)
        binding.recyclerUsers.adapter = adapter

        // Update the Ui when data the changes
        userViewModel.user.observe(this, Observer {
            it?.apply {
//                adapter.updateUsers(it.usersData)
                adapter.refreshUsers(it.usersData)
            }
        })
    }
}
