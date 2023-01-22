package com.nohjunh.airpollutionservice.foreground.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.nohjunh.airpollutionservice.R
import com.nohjunh.airpollutionservice.database.entity.CityAirPollutionEntity
import com.nohjunh.airpollutionservice.repository.DataBaseRepository
import com.nohjunh.airpollutionservice.view.main.MainActivity
import kotlinx.coroutines.*
import timber.log.Timber
import java.util.*

class ForeGroundService : Service() {

    private val setId = 1050
    private val dataBaseRepository = DataBaseRepository()

    lateinit var foregroundJob : Job

    override fun onCreate() {
        super.onCreate()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {

            "StartForeground" -> {
                foregroundJob = CoroutineScope(Dispatchers.Default).launch {
                    while (isActive) { // 코루틴 명시적 취소 상태 확인 -> cancel() 시 false
                        // start !!
                        try {
                            startForeground(setId, createNotification())
                            delay(1500)
                        } catch (e: java.lang.Exception) { Timber.d("foreGroundService Error")}
                    }
                }
            }

            "StopForeground" -> {
                try {
                    foregroundJob.cancel()
                    // stop !!
                    stopForeground(true)
                    stopSelf()
                } catch (e: java.lang.Exception) { Timber.d("foreGroundService Error")}
            }

        }
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    suspend fun createNotification() : Notification {

        val result = getCityAirPollutionData()

        val random = Random().nextInt(result.size)

        val content = "미세먼지 : ${result[random].pm10Grade}  |  초미세먼지 : ${result[random].pm25Grade}"
        val title = result[random].sidoName.plus(" ${result[random].stationName}")

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent : PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(this, "ChannelId")
            .setSmallIcon(R.drawable.splashimg)
            .setContentText(content)
            .setContentTitle(title)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "name"
            val descriptionText = "descriptionText"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel("ChannelId", name, importance).apply {
                description = descriptionText
            }

            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        return builder.build()

    }

    suspend fun getCityAirPollutionData(): List<CityAirPollutionEntity> {
        return dataBaseRepository.getAirPollutionDataList()
    }



}