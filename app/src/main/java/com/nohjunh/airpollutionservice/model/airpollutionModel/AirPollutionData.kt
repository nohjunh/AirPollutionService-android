package com.nohjunh.airpollutionservice.model.airpollutionModel


import com.google.gson.annotations.SerializedName

data class AirPollutionData(
    @SerializedName("response")
    val response: Response?
)