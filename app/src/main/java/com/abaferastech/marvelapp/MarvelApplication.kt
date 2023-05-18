package com.abaferastech.marvelapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarvelApplication : Application() {
    /*override fun onCreate() {
        super.onCreate()

        val refreshWorkerRequest = PeriodicWorkRequestBuilder<RefreshWorker>(
            repeatInterval = 24L,
            repeatIntervalTimeUnit = TimeUnit.HOURS
        )
            .setConstraints(Constraints.NONE)
            .build()

        WorkManager.getInstance(applicationContext).enqueue(refreshWorkerRequest)
    }*/
}