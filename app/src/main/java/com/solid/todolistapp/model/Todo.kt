package com.solid.todolistapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "todo_table")
data class Todo (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val taskName: String,
    val category: String,
    val priority: String,
    val timeStamp: String,
    val finished: Boolean,
    val categoryCardColor: Int,
    val priorityCardColor: Int
): Parcelable