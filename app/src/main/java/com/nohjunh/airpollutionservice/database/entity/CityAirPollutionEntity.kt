package com.nohjunh.airpollutionservice.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "select_city_table")
data class CityAirPollutionEntity (

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val sidoName: String,
    val stationName : String,
    val pm10Grade : String,
    val pm10Value: Int,
    val pm25Grade : String,
    val pm25Value : Int,
    var selected : Boolean
)