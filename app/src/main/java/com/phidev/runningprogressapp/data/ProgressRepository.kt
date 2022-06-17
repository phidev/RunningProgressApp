package com.phidev.runningprogressapp.data

import androidx.lifecycle.LiveData
import com.phidev.runningprogressapp.data.entity.Progress

class ProgressRepository(private val progressDao: ProgressDao) {
    val readAllData: LiveData<List<Progress>> = progressDao.readAllData()

    suspend fun addProgress(progress: Progress) {
        progressDao.addProgress(progress)
    }

}