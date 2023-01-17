package com.nohjunh.airpollutionservice.network

import com.nohjunh.airpollutionservice.BuildConfig
import com.nohjunh.airpollutionservice.model.airpollutionModel.AirPollutionDataList
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs {

    // # 한국환경공단_에어코리아_대기오염정보
    @GET("B552584/ArpltnInforInqireSvc/")
    suspend fun getAirPollutionDataList(
        @Query("serviceKey") ServiceKey:String = BuildConfig.APIKEY_AirPollution,
        @Query("returnType") ReturnType:String = "json",
        @Query("numOfRows") NumOfRows:Int = 100,
        @Query("pageNo") PageNo:Int = 1,
        @Query("sidoName") SidoName:String = "부산",
        @Query("ver") Ver:String = "1.0"
    ) : AirPollutionDataList

}