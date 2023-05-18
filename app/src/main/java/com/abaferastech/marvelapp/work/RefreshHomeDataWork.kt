package com.abaferastech.marvelapp.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

//class RefreshDataWork(appContext: Context, params: WorkerParameters) :
//    CoroutineWorker(appContext, params) {
//    override suspend fun doWork(): Result {
//        TODO("Not yet implemented")
//    }
//}

//
//class RefreshWorker @WorkerInject constructor(
//    @Assisted appContext: Context,
//    @Assisted workerParams: WorkerParameters,
//    private val repository: MarvelRepository
//) : CoroutineWorker(appContext, workerParams) {
//
//    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
//        try {
//            repository.refreshCharacters()
//            Result.success()
//        } catch (e: Throwable) {
//            Result.failure()
//        }
//    }
//}


//
//@HiltWorker
//class RefreshWorker @AssistedInject constructor(
//    @Assisted appContext: Context,
//    @Assisted workerParams: WorkerParameters,
//   val repository: MarvelRepository
//) : CoroutineWorker(appContext, workerParams) {
//
//    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
//        try {
//            repository.refreshCharacters()
//            Result.success()
//        } catch (e: Throwable) {
//            Result.failure()
//        }
//    }
//}
//
//class RefreshCharactersWorker(
//    appContext: Context,
//    workerParams: WorkerParameters,
//    private val marvelRepository: MarvelRepository
//) : Worker(appContext, workerParams) {
//
//    override fun doWork(): Result {
//        marvelRepository.refreshCharacters()
//        return Result.success()
//    }
//
//    companion object {
//        private const val WORK_TAG = "refresh_characters_work"
//
//        fun scheduleWork(context: Context) {
//            val constraints = Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .build()
//
//            val workRequest = PeriodicWorkRequestBuilder<RefreshCharactersWorker>(
//                repeatInterval = 24,
//                repeatIntervalTimeUnit = TimeUnit.HOURS
//            )
//                .setConstraints(constraints)
//                .addTag(WORK_TAG)
//                .build()
//
//            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
//                WORK_TAG,
//                ExistingPeriodicWorkPolicy.KEEP,
//                workRequest
//            )
//        }
//    }
//}




@HiltWorker
class RefreshWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: MarvelRepository
) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        return try {
            repository.refreshCharacters()
            Result.success()
        } catch (e: Throwable) {
            Result.failure()
        }
    }
}
