package com.phidev.runningprogressapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "progress_table")
data class Progress(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @NotNull @ColumnInfo(name = "date") val date: String,
    @NotNull @ColumnInfo(name = "distance") val distance: String,
    @NotNull @ColumnInfo(name = "duration") val duration: String,
)
