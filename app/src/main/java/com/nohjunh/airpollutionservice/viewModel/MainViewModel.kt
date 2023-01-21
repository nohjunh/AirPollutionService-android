package com.nohjunh.airpollutionservice.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nohjunh.airpollutionservice.adapter.CityListRVAdapter
import com.nohjunh.airpollutionservice.database.entity.CityAirPollutionEntity
import com.nohjunh.airpollutionservice.model.airpollutionModel.AirPollutionDataList

import com.nohjunh.airpollutionservice.repository.DataBaseRepository
import com.nohjunh.airpollutionservice.repository.NetWorkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val netWorkRepository = NetWorkRepository()
    private val dataBaseRepository = DataBaseRepository()
    lateinit var selectedCityAirList : LiveData<List<CityAirPollutionEntity>>

    fun getCityAirPollutionData() = viewModelScope.launch {

        // LiveData로 만들어줌
        val cityAirList = dataBaseRepository.getSelectedCityAirPollutionData().asLiveData()
        selectedCityAirList = cityAirList
    }


    /* 관심 city 목록 변경 */
    fun insertSelectedCityListData(sidoName: String) = viewModelScope.launch(
        Dispatchers.IO) {
        val result = netWorkRepository.getAirPollutionData(sidoName)

        for (data in result.response!!.body!!.items) {
            try {
                lateinit var pm10GradeData: String
                lateinit var pm25GradeData: String

                when (data.pm10Grade) {
                    "1" -> pm10GradeData = "좋음"
                    "2" -> pm10GradeData = "보통"
                    "3" -> pm10GradeData = "나쁨"
                    "4" -> pm10GradeData = "매우나쁨"
                }

                when (data.pm25Grade) {
                    "1" -> pm25GradeData = "좋음"
                    "2" -> pm25GradeData = "보통"
                    "3" -> pm25GradeData = "나쁨"
                    "4" -> pm25GradeData = "매우나쁨"
                }

                val tempData = AirPollutionDataList(
                    data.sidoName,
                    data.stationName,
                    pm10GradeData,
                    Integer.parseInt(data.pm10Value),
                    pm25GradeData,
                    Integer.parseInt(data.pm25Value)
                )

                val selected = true

                val cityAirPollutionEntity = CityAirPollutionEntity(
                    0,
                    tempData.sidoName,
                    tempData.stationName,
                    tempData.pm10Grade,
                    tempData.pm10Value,
                    tempData.pm25Grade,
                    tempData.pm25Value,
                    selected
                )

                cityAirPollutionEntity.let {
                    dataBaseRepository.insertSelectedCityAirPollutionData(it)
                }
            } catch (e: java.lang.Exception) { Timber.d("getAirPollutionDataList Error")}
        }
    }
    fun deleteSelectedCityListData(sidoName: String) = viewModelScope.launch(
        Dispatchers.IO) {

        dataBaseRepository.deleteSelectedCityAirPollutionData(sidoName)
    }
}