package com.nohjunh.airpollutionservice.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// object로 class 정의 -> singleton 적용 -> 객체가 한 번만 생성
object RetrofitInstance {

    private const val BASE_URL = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/"

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20,TimeUnit.SECONDS)
        .writeTimeout(20,TimeUnit.SECONDS)
        .build()


    var gson = GsonBuilder().setLenient().create()
    private val client = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getInstance() : Retrofit {
        return client
    }

}
