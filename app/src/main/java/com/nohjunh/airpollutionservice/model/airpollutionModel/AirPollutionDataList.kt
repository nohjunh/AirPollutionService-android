package com.nohjunh.airpollutionservice.model.airpollutionModel


import com.google.gson.annotations.SerializedName

data class AirPollutionDataList(
    @SerializedName("response")
    val response: Response?
)