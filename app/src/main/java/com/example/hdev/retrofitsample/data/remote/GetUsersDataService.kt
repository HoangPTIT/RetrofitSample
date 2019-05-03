package com.example.hdev.retrofitsample.data.remote

import com.example.hdev.retrofitsample.data.models.Users
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GetUsersDataService {
    @GET("json/retrofit-demo.php")
    fun getUsersData(@Query("company_no") companyNo: Int): Observable<Users>
}
