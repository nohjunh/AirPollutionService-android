package com.nohjunh.airpollutionservice.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nohjunh.airpollutionservice.R
import timber.log.Timber

class PreviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        Timber.tag("PreviewActivity").e("start")
    }
}