package com.nohjunh.airpollutionservice.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjunh.airpollutionservice.database.entity.CityAirPollutionEntity
import com.nohjunh.airpollutionservice.model.airpollutionModel.AirPollutionDataList
import com.nohjunh.airpollutionservice.repository.DataBaseRepository
import com.nohjunh.airpollutionservice.repository.NetWorkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class AirPollutionViewModel : ViewModel() {

    private val netWorkRepository = NetWorkRepository()
    private val dataBaseRepository = DataBaseRepository()

    lateinit var airPollutionDataList : ArrayList<AirPollutionDataList>
    lateinit var airPollutionCheckList : ArrayList<String>

    // LiveData -> 실시간 데이터 observe
    private val _airPollutionLiveData = MutableLiveData<List<AirPollutionDataList>>()
    val airPollutionLiveData : LiveData<List<AirPollutionDataList>>
        get() = _airPollutionLiveData

    // Room DB에 공공데이터포털 미세먼지 데이터가 다 저장됐는지 파악하기 위한 LiveData
    private val _saved = MutableLiveData<String>()
    val save : LiveData<String>
        get() = _saved

    private var finish = false

    fun getAirPollutionDataList(checksStarRegionList: ArrayList<String>) = viewModelScope.launch {
        airPollutionCheckList = ArrayList()
        airPollutionDataList = ArrayList()

        for (sidoName in checksStarRegionList) {
            val result = netWorkRepository.getAirPollutionData(sidoName)

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
                    airPollutionCheckList.add(sidoName)

                } catch (e: java.lang.Exception) { Timber.d("getAirPollutionDataList Error")}
            }
        }
        _airPollutionLiveData.value= airPollutionDataList
        finish = true
    }

    // Room DataBase에 선택한 지역리스트 데이터 최신화
    fun saveSelectedCityList(checksStarRegionList: ArrayList<String>) = viewModelScope.launch (Dispatchers.IO) {

        getAirPollutionDataList(checksStarRegionList)

        while (!finish) { }
        finish = false

        for (data in airPollutionDataList) {

            val selected = true

            val cityAirPollutionEntity = CityAirPollutionEntity(
                0,
                data.sidoName,
                data.stationName,
                data.pm10Grade,
                data.pm10Value,
                data.pm25Grade,
                data.pm25Value,
                selected
            )

            cityAirPollutionEntity.let {
                dataBaseRepository.insertSelectedCityAirPollutionData(it)
            }
        }

        withContext(Dispatchers.Main) {
            _saved.value = "pushDataToDBFinish"
        }

    }


}