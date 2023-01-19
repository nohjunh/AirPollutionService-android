package com.nohjunh.airpollutionservice.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nohjunh.airpollutionservice.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.tag("MainActivity").e("start")
    }
}