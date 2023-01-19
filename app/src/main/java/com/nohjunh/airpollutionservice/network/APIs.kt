package com.nohjunh.airpollutionservice.network

import com.nohjunh.airpollutionservice.BuildConfig
import com.nohjunh.airpollutionservice.model.airpollutionModel.AirPollutionData
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs {

    // # 한국환경공단_에어코리아_대기오염정보
    @GET("getCtprvnRltmMesureDnsty")
    suspend fun getAirPollutionData(
        @Query("serviceKey") ServiceKey:String = BuildConfig.APIKEY_AirPollution,
        @Query("returnType") ReturnType:String = "json",
        @Query("numOfRows") NumOfRows:Int = 15,
        @Query("pageNo") PageNo:Int = 1,
        @Query("sidoName") SidoName:String,
        @Query("ver") Ver:String = "1.0"
    ) : AirPollutionData

}