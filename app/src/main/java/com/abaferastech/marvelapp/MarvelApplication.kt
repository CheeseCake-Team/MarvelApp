package com.abaferastech.marvelapp

import android.app.Application
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.abaferastech.marvelapp.work.RefreshWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val refreshWorkerRequest = PeriodicWorkRequestBuilder<RefreshWorker>(
            repeatInterval = 24L,
            repeatIntervalTimeUnit = TimeUnit.HOURS
        )
            .setConstraints(Constraints.NONE)
            .build()

        WorkManager.getInstance(applicationContext).enqueue(refreshWorkerRequest)
    }
}