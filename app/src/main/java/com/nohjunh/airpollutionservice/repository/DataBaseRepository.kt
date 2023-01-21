package com.nohjunh.airpollutionservice.repository

import com.nohjunh.airpollutionservice.App
import com.nohjunh.airpollutionservice.database.AirPollutionDatabase
import com.nohjunh.airpollutionservice.database.entity.CityAirPollutionEntity

class DataBaseRepository {

    val context = App.context()
    val airPollutionDB = AirPollutionDatabase.getDatabase(context)

    fun getSelectedCityAirPollutionData() = airPollutionDB.cityAirPollutionDAO().getAirPollutionData()

    fun insertSelectedCityAirPollutionData(cityAirPollutionEntity: CityAirPollutionEntity) = airPollutionDB.cityAirPollutionDAO().insert(cityAirPollutionEntity)

    fun updateSelectedCityAirPollutionData(cityAirPollutionEntity: CityAirPollutionEntity) = airPollutionDB.cityAirPollutionDAO().update(cityAirPollutionEntity)

    fun deleteSelectedCityAirPollutionData(sidoName : String) = airPollutionDB.cityAirPollutionDAO().delete(sidoName)

    fun getSelectedCityListData() = airPollutionDB.cityAirPollutionDAO().getCityListData()

    fun updateSelectedCityData(sidoName: String, stationName : String, pm10Grade : String, pm10Value: Int, pm25Grade : String, pm25Value : Int) = airPollutionDB.cityAirPollutionDAO().updateAirData(sidoName, stationName, pm10Grade, pm10Value, pm25Grade, pm25Value)
}