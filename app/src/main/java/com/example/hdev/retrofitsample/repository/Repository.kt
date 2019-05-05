package com.example.hdev.retrofitsample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hdev.retrofitsample.data.models.Users
import com.example.hdev.retrofitsample.data.remote.GetUsersDataService
import com.example.hdev.retrofitsample.data.remote.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Repository {

    private val userDataService by lazy {
        RetrofitInstance.getRetrofitInstance().create(GetUsersDataService::class.java)
    }

    fun getUsers(companyNo: Int): LiveData<Users> {
        val data: MutableLiveData<Users> by lazy {
            MutableLiveData<Users>()
        }

        val disposable = userDataService.getUsersData(companyNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    data.value = it
                },
                {
                    data.value = null
                }
            )

        return data
    }

    companion object {
        private val repository: Repository? = null

        fun getInstance() = repository ?: synchronized(this) {
            repository ?: Repository()
        }
    }
}
