package com.nohjunh.airpollutionservice.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjunh.airpollutionservice.dataStore.FlagDataStore
import kotlinx.coroutines.launch

class RegionViewModel : ViewModel() {

    lateinit var cityList : ArrayList<String>

    fun getCityList() = viewModelScope.launch {
        cityList = ArrayList()

        cityList.add("서울특별시")
        cityList.add("부산광역시")
        cityList.add("대구광역시")
        cityList.add("인천광역시")
        cityList.add("광주광역시")
        cityList.add("대전광역시")
        cityList.add("울산광역시")
        cityList.add("부산광역시")
        cityList.add("경기도")
        cityList.add("강원도")
        cityList.add("충청북도")
        cityList.add("충청남도")
        cityList.add("전라북도")
        cityList.add("전라남도")
        cityList.add("경상북도")
        cityList.add("경상남도")
        cityList.add("제주특별자치도")
        cityList.add("세종특별자치시")
    }

    // 처음 앱을 구동시켰는지 여부를 판단하기 위한 메소드
    fun setFlagData() = viewModelScope.launch {
        FlagDataStore().setFlagData()
    }
}