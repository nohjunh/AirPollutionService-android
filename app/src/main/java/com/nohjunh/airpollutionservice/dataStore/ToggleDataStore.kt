package com.nohjunh.airpollutionservice.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.nohjunh.airpollutionservice.App

class ToggleDataStore {

    private val context = App.context()

    companion object {
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("toggleState")
    }

    private val flagDataStore : DataStore<Preferences> = context.dataStore
    private val flagValue = booleanPreferencesKey("toggleKey")

    // 현재 flag값 가져오기
    suspend fun getFlagData() : Boolean {
        var curValue = false

        flagDataStore.edit { pref ->
            curValue = pref[flagValue] ?: false
        }
        return curValue
    }

    // toggle 클릭 시
    suspend fun setFlagData() {
        flagDataStore.edit { pref ->
            if (pref[flagValue] == true) {
                pref[flagValue] = false
            } else {
                pref[flagValue] = true
            }
        }
    }
}