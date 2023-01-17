package com.nohjunh.airpollutionservice.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nohjunh.airpollutionservice.R
import com.nohjunh.airpollutionservice.RegionAdapter
import com.nohjunh.airpollutionservice.databinding.ActivityRegionBinding
import com.nohjunh.airpollutionservice.viewModel.RegionViewModel

class RegionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegionBinding

    private val viewModel : RegionViewModel by viewModels()

    private lateinit var regionRVAdapter : RegionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCityList()
        regionRVAdapter = RegionAdapter(this, viewModel.cityList)
        binding.regionRV.adapter = regionRVAdapter
        binding.regionRV.layoutManager = LinearLayoutManager(this)

    }
}