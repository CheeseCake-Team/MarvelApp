package com.abaferastech.marvelapp.work

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import retrofit2.HttpException


class RefreshCharactersWorker (
    appContext: Context,
    workerParams: WorkerParameters,
) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        return try {
        Log.d("MAMO", "doWork: called")
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }

    companion object {
        const val WORK_TAG = "refresh_characters_work"
    }
}

