package com.nohjunh.airpollutionservice.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjunh.airpollutionservice.dataStore.FlagDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class PreviewViewModel : ViewModel() {

    private val _flag = MutableLiveData<Boolean>()
    val flag : LiveData<Boolean>
        get() = _flag

    fun flagDataAccessCheck() = viewModelScope.launch {
        // lottie aniView delay
        delay(3000)

        val flagData = FlagDataStore().getFlagData()
        _flag.value = flagData

        // false 이면 처음 접속(firstAccess)
        if (flagData) {
            Timber.tag("flagData").e("NotfirstAccess")
        } else {
            Timber.tag("flagData").e("firstAccess")
        }
    }

}