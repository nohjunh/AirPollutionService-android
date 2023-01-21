package com.nohjunh.airpollutionservice.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nohjunh.airpollutionservice.database.entity.CityAirPollutionEntity
import com.nohjunh.airpollutionservice.model.airpollutionModel.AirPollutionDataList
import com.nohjunh.airpollutionservice.repository.DataBaseRepository
import com.nohjunh.airpollutionservice.repository.NetWorkRepository
import timber.log.Timber

// workManager
// 주기적으로 API를 호출해 새롭게 반영된 data를 background작업으로 최신화함.
class GetAirDataWorkManager(val context: Context, workerParameters: WorkerParameters)
    :CoroutineWorker(context, workerParameters) {

    private val dataBaseRepository = DataBaseRepository()
    private val netWorkRepository = NetWorkRepository()

    override suspend fun doWork() : Result {

        getSelectedCityAirPollutionData()

        return Result.success()
    }

    suspend fun getSelectedCityAirPollutionData() {

        var airPollutionDataList : ArrayList<AirPollutionDataList> = ArrayList()

        val data = dataBaseRepository.getSelectedCityListData()

        for(sidoName in data) {
            Timber.tag("FOREST").e(sidoName)

            val result = netWorkRepository.getAirPollutionData(sidoName)

            for (city in result.response!!.body!!.items) {
                try {
                    lateinit var pm10GradeData: String
                    lateinit var pm25GradeData: String

                    when (city.pm10Grade) {
                        "1" -> pm10GradeData = "좋음"
                        "2" -> pm10GradeData = "보통"
                        "3" -> pm10GradeData = "나쁨"
                        "4" -> pm10GradeData = "매우나쁨"
                    }

                    when (city.pm25Grade) {
                        "1" -> pm25GradeData = "좋음"
                        "2" -> pm25GradeData = "보통"
                        "3" -> pm25GradeData = "나쁨"
                        "4" -> pm25GradeData = "매우나쁨"
                    }

                    val tempData = AirPollutionDataList(
                        city.sidoName,
                        city.stationName,
                        pm10GradeData,
                        Integer.parseInt(city.pm10Value),
                        pm25GradeData,
                        Integer.parseInt(city.pm25Value)
                    )
                    airPollutionDataList.add(tempData)
                } catch (e: java.lang.Exception) { Timber.d("getAirPollutionDataList Error")}
            }

            for (city in airPollutionDataList) {
                dataBaseRepository.updateSelectedCityData(city.sidoName, city.stationName, city.pm10Grade, city.pm10Value, city.pm25Grade, city.pm25Value)
            }

        }
    }

}