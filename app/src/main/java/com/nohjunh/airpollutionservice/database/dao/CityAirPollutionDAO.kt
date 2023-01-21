package com.nohjunh.airpollutionservice.database.dao

import androidx.room.*
import com.nohjunh.airpollutionservice.database.entity.CityAirPollutionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityAirPollutionDAO {

    @Query("SELECT * FROM select_city_table WHERE selected = :selected")
    fun getAirPollutionData(selected : Boolean =true) : Flow<List<CityAirPollutionEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cityAirPollutionEntity: CityAirPollutionEntity)

    @Update
    fun update(cityAirPollutionEntity: CityAirPollutionEntity)

    @Query("DELETE FROM select_city_table WHERE sidoName = :sidoName")
    fun delete(sidoName: String)

    @Query("SELECT DISTINCT sidoName FROM select_city_table")
    fun getCityListData() : List<String>

    @Query("UPDATE select_city_table SET pm10Grade = :pm10Grade, pm10Value = :pm10Value, pm25Grade = :pm25Grade, pm25Value = :pm25Value WHERE sidoName = :sidoName AND stationName = :stationName")
    fun updateAirData(sidoName: String, stationName : String, pm10Grade : String, pm10Value: Int, pm25Grade : String, pm25Value : Int)

}