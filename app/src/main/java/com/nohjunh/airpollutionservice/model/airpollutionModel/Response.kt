package com.nohjunh.airpollutionservice.model.airpollutionModel


import com.google.gson.annotations.SerializedName

data class Response(
    val body: Body,
    val header: Header
)