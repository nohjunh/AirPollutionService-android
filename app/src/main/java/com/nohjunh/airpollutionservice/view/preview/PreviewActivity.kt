package com.nohjunh.airpollutionservice.view.preview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.nohjunh.airpollutionservice.databinding.ActivityPreviewBinding
import com.nohjunh.airpollutionservice.view.main.MainActivity
import com.nohjunh.airpollutionservice.viewModel.PreviewViewModel
import timber.log.Timber

class PreviewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPreviewBinding

    private val viewModel : PreviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.tag("PreviewActivity").e("start")

        viewModel.flagDataAccessCheck()

        viewModel.flag.observe(this, Observer {
            if (it == true) { // NotFirstAccess
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                binding.LottieView.visibility = View.INVISIBLE
                // Preview Activity가 보이도록 설정
                binding.fragmentContainerView.visibility = View.VISIBLE
            }
        })

    }
}