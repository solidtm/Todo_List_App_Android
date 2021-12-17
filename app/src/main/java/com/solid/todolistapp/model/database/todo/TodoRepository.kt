package com.solid.todolistapp.model.database.todo

import androidx.lifecycle.LiveData
import com.solid.todolistapp.model.data.Todo

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