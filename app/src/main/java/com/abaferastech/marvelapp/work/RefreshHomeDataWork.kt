package com.abaferastech.marvelapp.work

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.lifecycle.ViewModelProvider
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.abaferastech.marvelapp.ui.home.HomeViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import okhttp3.OkHttpClient
import retrofit2.HttpException


@HiltWorker
class HomeUpdaterWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams) {

    val viewModel: HomeViewModel = ViewModelProvider
        .AndroidViewModelFactory
        .getInstance(applicationContext as Application)
        .create(HomeViewModel::class.java)



    override suspend fun doWork(): Result {
        return try {
            Log.i("X101", "Created")
            viewModel.requestRepoData()
            Log.i("X101", "Done")
            Result.success()
        } catch (e: HttpException) {
            Log.e("X101", e.message.toString())
            Result.retry()
        }
    }

    companion object {
        const val WORK_TAG = "refresh_characters_work"
    }
}

