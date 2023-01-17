package com.nohjunh.airpollutionservice.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjunh.airpollutionservice.repository.NetWorkRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class AirPollutionViewModel : ViewModel() {

    private val netWorkRepository = NetWorkRepository()

    fun getAirPollutionDataList() = viewModelScope.launch {

        val result = netWorkRepository.getAirPollutionDataList()

        Timber.d(result.toString())
    }
}