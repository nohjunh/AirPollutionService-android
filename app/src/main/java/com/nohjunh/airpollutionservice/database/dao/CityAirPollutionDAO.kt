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
}