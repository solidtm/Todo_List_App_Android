package com.solid.todolistapp.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val taskName: String,
    val category: String,
    val priority: String,
    val timeStamp: String,
    val finished: Boolean
)