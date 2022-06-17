package com.phidev.runningprogressapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.phidev.runningprogressapp.data.entity.Progress

@Database(entities = [Progress::class], version = 1, exportSchema = false)
abstract class ProgressDatabase : RoomDatabase() {
    abstract fun progressDao(): ProgressDao

    companion object {
        @Volatile
        private var INSTANCE: ProgressDatabase? = null

        fun getDatabase(context: Context): ProgressDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProgressDatabase::class.java,
                    "progress_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }

    }
}