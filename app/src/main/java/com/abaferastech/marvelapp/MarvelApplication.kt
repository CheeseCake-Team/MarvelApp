package com.abaferastech.marvelapp

import android.app.Application
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.abaferastech.marvelapp.work.RefreshCharactersWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<RefreshCharactersWorker>(
            repeatInterval = 3,
            repeatIntervalTimeUnit = TimeUnit.SECONDS
        )
            .setConstraints(constraints)
            .addTag(RefreshCharactersWorker.WORK_TAG)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            RefreshCharactersWorker.WORK_TAG,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )

    }
}