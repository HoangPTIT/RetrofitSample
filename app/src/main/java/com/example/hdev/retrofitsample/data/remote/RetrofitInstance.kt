package com.example.hdev.retrofitsample.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private var retrofit: Retrofit? = null
        private const val BASE_URL = "http://navjacinth9.000webhostapp.com/"

        fun getRetrofitInstance(): Retrofit {
            //TODO: Singleton
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}
