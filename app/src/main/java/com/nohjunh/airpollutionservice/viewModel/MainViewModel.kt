package com.nohjunh.airpollutionservice.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nohjunh.airpollutionservice.database.entity.CityAirPollutionEntity

import com.nohjunh.airpollutionservice.repository.DataBaseRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val dataBaseRepository = DataBaseRepository()
    lateinit var selectedCityAirList : LiveData<List<CityAirPollutionEntity>>

    fun getCityAirPollutionData() = viewModelScope.launch {

        // LiveData로 만들어줌
        val cityAirList = dataBaseRepository.getSelectedCityAirPollutionData().asLiveData()
        selectedCityAirList = cityAirList
    }

}