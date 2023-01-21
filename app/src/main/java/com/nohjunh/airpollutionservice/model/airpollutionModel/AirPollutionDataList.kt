package com.nohjunh.airpollutionservice.model.airpollutionModel

data class AirPollutionDataList (

    val sidoName: String,
    val stationName : String,
    val pm10Grade : String,
    var pm10Value: Int,
    val pm25Grade : String,
    val pm25Value : Int
)