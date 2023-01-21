package com.nohjunh.airpollutionservice.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjunh.airpollutionservice.dataStore.FlagDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegionViewModel : ViewModel() {

    lateinit var cityList : ArrayList<String>

    fun getCityList() = viewModelScope.launch {
        cityList = ArrayList()

        cityList.add("서울")
        cityList.add("부산")
        cityList.add("대구")
        cityList.add("인천")
        cityList.add("광주")
        cityList.add("대전")
        cityList.add("울산")
        cityList.add("부산")
        cityList.add("경기")
        cityList.add("강원")
        cityList.add("충북")
        cityList.add("충남")
        cityList.add("전북")
        cityList.add("전남")
        cityList.add("경북")
        cityList.add("경남")
        cityList.add("제주")
        cityList.add("세종")
    }

    // 처음 앱을 구동시켰는지 여부를 판단하기 위한 메소드
    fun setFlagData() = viewModelScope.launch {
        FlagDataStore().setFlagData()
    }

}