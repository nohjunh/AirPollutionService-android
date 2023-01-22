package com.nohjunh.airpollutionservice.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjunh.airpollutionservice.dataStore.FlagDataStore
import com.nohjunh.airpollutionservice.dataStore.ToggleDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class foregroundViewModel : ViewModel() {

    private val _flag = MutableLiveData<Boolean>()
    val flag : LiveData<Boolean>
        get() = _flag

    fun flagToggleDataCheck() = viewModelScope.launch {

        val flagData = ToggleDataStore().getFlagData()
        _flag.value = flagData

        // true이면 toggle이 true인 상태로 이미지를 변경해줘야 함.
        if (flagData) {
            Timber.tag("flagData").e("toggleOnState")
        } else {
            Timber.tag("flagData").e("toggleOffState")
        }
    }

    fun flagSetToggleData() = viewModelScope.launch {
        ToggleDataStore().setFlagData()
    }
}