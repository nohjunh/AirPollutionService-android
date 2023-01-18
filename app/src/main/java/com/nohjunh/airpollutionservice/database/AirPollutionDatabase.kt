package com.nohjunh.airpollutionservice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nohjunh.airpollutionservice.database.dao.CityAirPollutionDAO
import com.nohjunh.airpollutionservice.database.entity.CityAirPollutionEntity
import com.nohjunh.airpollutionservice.model.airpollutionModel.AirPollutionDataList

@Database(entities = [CityAirPollutionEntity::class], version = 1)
abstract class AirPollutionDatabase : RoomDatabase() {

    abstract fun cityAirPollutionDAO() : CityAirPollutionDAO

    companion object {

        @Volatile
        private var INSTANCE : AirPollutionDatabase? =null

        fun getDatabase(context : Context) : AirPollutionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AirPollutionDatabase::class.java,
                    "cityDB"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}