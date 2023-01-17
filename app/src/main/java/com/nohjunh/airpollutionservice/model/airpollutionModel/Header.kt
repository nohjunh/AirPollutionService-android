package com.nohjunh.airpollutionservice.model.airpollutionModel


import com.google.gson.annotations.SerializedName

data class Header(
    val resultCode: String,
    val resultMsg: String
)