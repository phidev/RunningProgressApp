package com.phidev.runningprogressapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.phidev.runningprogressapp.data.entity.Progress

@Dao
interface ProgressDao {
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun addProgress(progress: Progress)

   @Query("SELECT * FROM progress_table ORDER BY id ASC")
   fun readAllData():LiveData<List<Progress>>

}