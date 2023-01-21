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
}