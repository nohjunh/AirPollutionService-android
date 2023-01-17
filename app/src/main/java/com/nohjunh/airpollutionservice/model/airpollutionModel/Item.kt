package com.nohjunh.airpollutionservice.model.airpollutionModel


import com.google.gson.annotations.SerializedName

data class Item(
    val coFlag: String,
    val coGrade: String,
    val coValue: String,
    val dataTime: String,
    val khaiGrade: String,
    val khaiValue: String,
    val no2Flag: String,
    val no2Grade: String,
    val no2Value: String,
    val o3Flag: String,
    val o3Grade: String,
    val o3Value: String,
    val pm10Flag: String,
    val pm10Grade: String,
    val pm10Value: String,
    val pm25Flag: String,
    val pm25Grade: String,
    val pm25Value: String,
    val sidoName: String,
    val so2Flag: String,
    val so2Grade: String,
    val so2Value: String,
    val stationName: String
)