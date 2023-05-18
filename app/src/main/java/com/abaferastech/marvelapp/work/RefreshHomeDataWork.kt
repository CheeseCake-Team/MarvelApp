package com.abaferastech.marvelapp.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.abaferastech.marvelapp.data.repository.MarvelRepository


class RefreshCharactersWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val marvelRepository: MarvelRepository
) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        marvelRepository.refreshHome()
        return Result.success()
    }

    companion object {
        const val WORK_TAG = "refresh_characters_work"
    }
}

