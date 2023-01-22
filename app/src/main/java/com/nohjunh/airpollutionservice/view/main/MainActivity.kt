package com.nohjunh.airpollutionservice.view.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.nohjunh.airpollutionservice.R
import com.nohjunh.airpollutionservice.databinding.ActivityMainBinding
import com.nohjunh.airpollutionservice.foreground.ForeGroundActivity
import com.nohjunh.airpollutionservice.view.region.RegionActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    companion object {
        var toastContext : Context? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.tag("MainActivity").e("start")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        toastContext = applicationContext
        setContentView(binding.root)

        val bottomNavView = binding.bottomNavView
        val navController = findNavController(R.id.fragmentContainerView)

        bottomNavView.setupWithNavController(navController)

        binding.setting.setOnClickListener {
            val intent = Intent(this, ForeGroundActivity::class.java)
            startActivity(intent)
        }

    }
}