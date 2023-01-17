package com.nohjunh.airpollutionservice.model.airpollutionModel


import com.google.gson.annotations.SerializedName

data class Body(
    val items: List<Item>,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)