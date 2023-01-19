package com.nohjunh.airpollutionservice.view.region

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nohjunh.airpollutionservice.adapter.RegionAdapter
import com.nohjunh.airpollutionservice.databinding.ActivityRegionBinding
import com.nohjunh.airpollutionservice.view.MainActivity
import com.nohjunh.airpollutionservice.viewModel.AirPollutionViewModel
import com.nohjunh.airpollutionservice.viewModel.RegionViewModel
import timber.log.Timber

class RegionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegionBinding

    private val viewModel : RegionViewModel by viewModels()
    private val airPollutionViewModel : AirPollutionViewModel by viewModels()

    private lateinit var regionRVAdapter : RegionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCityList()
        regionRVAdapter = RegionAdapter(this, viewModel.cityList)
        binding.regionRV.adapter = regionRVAdapter
        binding.regionRV.layoutManager = LinearLayoutManager(this)

        binding.nextBtn.setOnClickListener {
            // 처음 접속했는지 아닌지 여부 판단
            viewModel.setFlagData()

            // AirPollutionViewModel로 선택한 도시 리스트를 보내줌
            airPollutionViewModel.saveSelectedCityList(regionRVAdapter.checksStarRegionList)

        }

        airPollutionViewModel.save.observe(this, Observer {
            if (it.equals("pushDataToDBFinish")) {
                // 메인 액티비티로 GoGo
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
           }
        })

    }
}