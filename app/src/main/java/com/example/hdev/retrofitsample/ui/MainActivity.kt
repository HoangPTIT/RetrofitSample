package com.example.hdev.retrofitsample.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hdev.retrofitsample.R.layout
import com.example.hdev.retrofitsample.data.models.User
import com.example.hdev.retrofitsample.data.remote.GetUsersDataService
import com.example.hdev.retrofitsample.data.remote.RetrofitInstance
import com.example.hdev.retrofitsample.data.viewmodels.UserViewModel
import com.example.hdev.retrofitsample.databinding.ActivityMainBinding
import com.example.hdev.retrofitsample.ui.adapter.UserAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.recyclerUsers

class MainActivity : AppCompatActivity() {

    private val usersService by lazy {
        RetrofitInstance.getRetrofitInstance().create(GetUsersDataService::class.java)
    }

    private val userViewModel by lazy { ViewModelProviders.of(this).get(UserViewModel::class.java) }

    private val call by lazy { usersService.getUsersData(100) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, layout.activity_main)
        binding.lifecycleOwner = this
        getUsers()
    }

    private fun getUsers() {
        val compositeDisposable = CompositeDisposable()
        val dispose: Disposable = call.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { users ->
                    displayUser(users.usersData)
                },
                { error ->
                    Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show()
                })
        compositeDisposable.add(dispose)
    }

    private fun displayUser(users: List<User>?) {
        if (users.isNullOrEmpty()) return
        val adapter = UserAdapter(users, userViewModel)
        recyclerUsers?.apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }
    }

    //TODO: Update list user
}
