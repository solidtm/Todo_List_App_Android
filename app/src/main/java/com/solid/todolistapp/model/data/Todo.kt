package com.solid.todolistapp.model.data

data class Todo (
    val taskName: String,
    val category: String,
    val priority: String,
    val timeStamp: String,
    val finished: Boolean
)