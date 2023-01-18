package com.nohjunh.airpollutionservice.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.nohjunh.airpollutionservice.App

class FlagDataStore {

    private val context = App.context()

    companion object {
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("FirstOrNotSetting")
    }

    private val flagDataStore : DataStore<Preferences> = context.dataStore
    private val flagValue = booleanPreferencesKey("FirstAccess")

    // 현재 flag값 가져오기
    suspend fun getFlagData() : Boolean {
        var curValue = false

        flagDataStore.edit { pref ->
            curValue = pref[flagValue] ?: false
        }

        return curValue
    }

    // 첫 번째 접속이 진행될 시 true 값 셋팅
    suspend fun setFlagData() {
        flagDataStore.edit { pref ->
            pref[flagValue] = true
        }
    }

}