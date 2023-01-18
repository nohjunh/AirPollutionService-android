package com.nohjunh.airpollutionservice.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjunh.airpollutionservice.model.airpollutionModel.AirPollutionDataList
import com.nohjunh.airpollutionservice.repository.NetWorkRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class AirPollutionViewModel : ViewModel() {

    private val netWorkRepository = NetWorkRepository()

    private lateinit var airPollutionDataList : ArrayList<AirPollutionDataList>

    // LiveData -> 실시간 데이터 observe
    private val _airPollutionLiveData = MutableLiveData<List<AirPollutionDataList>>()
    val airPollutionLiveData : LiveData<List<AirPollutionDataList>>
        get() = _airPollutionLiveData

    fun getAirPollutionDataList() = viewModelScope.launch {

        val result = netWorkRepository.getAirPollutionData()

        airPollutionDataList = ArrayList()


        for (data in result.response!!.body!!.items) {
            try {
                lateinit var pm10GradeData : String
                lateinit var pm25GradeData : String

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
                airPollutionDataList.add(tempData)

            } catch (e: java.lang.Exception) { Timber.d("getAirPollutionDataList Error")}
        }

        _airPollutionLiveData.value= airPollutionDataList

    }
}