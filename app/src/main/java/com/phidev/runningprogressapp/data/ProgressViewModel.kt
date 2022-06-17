package com.phidev.runningprogressapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.phidev.runningprogressapp.data.entity.Progress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProgressViewModel(application: Application) : AndroidViewModel(application) {
     val readAllData: LiveData<List<Progress>>
     private val repository: ProgressRepository

    init {
        val progressDAO = ProgressDatabase.getDatabase(application).progressDao()
        repository = ProgressRepository(progressDAO)
        readAllData = repository.readAllData
    }

    fun addProgress(progress: Progress) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProgress(progress)
        }
    }
}