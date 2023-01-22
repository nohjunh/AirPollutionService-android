package com.nohjunh.airpollutionservice.foreground.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.nohjunh.airpollutionservice.R
import com.nohjunh.airpollutionservice.databinding.ActivityForeGroundBinding
import com.nohjunh.airpollutionservice.foreground.service.ForeGroundService
import com.nohjunh.airpollutionservice.view.main.MainActivity
import com.nohjunh.airpollutionservice.viewModel.PreviewViewModel
import com.nohjunh.airpollutionservice.viewModel.foregroundViewModel

class ForeGroundActivity : AppCompatActivity() {

    private lateinit var binding : ActivityForeGroundBinding

    private val viewModel : foregroundViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForeGroundBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        viewModel.flagToggleDataCheck()
        viewModel.flag.observe(this, Observer {
            if (it == true) { // toggle이 true 상태임을 유지할 수 있도록 함.
                binding.switchButton.isChecked = true
            } else { // toggle이 false 상태임을 유지할 수 있도록 함.
                binding.switchButton.isChecked = false
            }
        })

        binding.switchButton.setOnClickListener {
            // toggle State 변경
            viewModel.flagSetToggleData()
        }

        binding.switchButton.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) { // 상단팝업 알림 시작
                val intent = Intent(this, ForeGroundService::class.java)
                intent.action = "StartForeground"
                startService(intent)
            } else { // 상단팝업 알림 종료
                val intent = Intent(this, ForeGroundService::class.java)
                intent.action = "StopForeground"
                startService(intent)
            }
        }

        // 뒤로 가기
        binding.backBtn.setOnClickListener {
            onBackPressed()
        }

    }

}