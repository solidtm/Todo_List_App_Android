package com.solid.todolistapp.model.database.todo

import androidx.lifecycle.LiveData
import com.solid.todolistapp.model.data.Todo

class TodoRepository(private val todoDao: TodoDao) {
    val readAllData: LiveData<List<Todo>> = todoDao.readAllData()

    suspend fun addTodo(todo: Todo){
        todoDao.addTodo(todo)
    }
}