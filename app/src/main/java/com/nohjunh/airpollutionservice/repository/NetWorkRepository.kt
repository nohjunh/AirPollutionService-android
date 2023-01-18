package com.nohjunh.airpollutionservice.repository

import com.nohjunh.airpollutionservice.network.APIs
import com.nohjunh.airpollutionservice.network.RetrofitInstance


// Repository를 만들어주는 이유는 ViewModel에서 network통신에 관여하는게 아니라
// viewModel은 Repository에만 관여하게끔 만들기 위함.
class NetWorkRepository {

    private val client = RetrofitInstance.getInstance().create(APIs::class.java)

    suspend fun getAirPollutionData() = client.getAirPollutionData()

}