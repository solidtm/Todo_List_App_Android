package com.solid.todolistapp.data.repository

import androidx.lifecycle.LiveData
import com.solid.todolistapp.data.model.Todo
import com.solid.todolistapp.data.db.dao.TodoDao

class TodoRepository(private val todoDao: TodoDao) {
    val readAllData: LiveData<List<Todo>> = todoDao.readAllData()

    fun addTodo(todo: Todo){
        todoDao.addTodo(todo)
    }

    fun updateTodo(todo: Todo){
        todoDao.updateTodo(todo)
    }

    fun deleteTodo(todo: Todo){
        todoDao.deleteTodo(todo)
    }
}