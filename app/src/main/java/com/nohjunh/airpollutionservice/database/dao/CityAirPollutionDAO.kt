package com.nohjunh.airpollutionservice.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nohjunh.airpollutionservice.database.entity.CityAirPollutionEntity
import kotlinx.coroutines.flow.Flow

interface CityAirPollutionDAO {

    @Query("SELECT * FROM select_city_table WHERE selected = :selected")
    fun getAirPollutionData(selected : Boolean =true) : Flow<List<CityAirPollutionEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cityAirPollutionEntity: CityAirPollutionEntity)

    @Update
    fun update(cityAirPollutionEntity: CityAirPollutionEntity)

}