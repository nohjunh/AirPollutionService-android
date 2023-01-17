package com.nohjunh.airpollutionservice.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// object로 class 정의 -> singleton 적용 -> 객체가 한 번만 생성
object RetrofitInstance {

    private const val BASE_URL = "http://apis.data.go.kr/"

    private val client = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstance() : Retrofit {
        return client
    }

}
