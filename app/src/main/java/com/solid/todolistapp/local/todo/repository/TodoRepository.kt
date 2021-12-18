package com.solid.todolistapp.local.todo.repository

import androidx.lifecycle.LiveData
import com.solid.todolistapp.model.Todo
import com.solid.todolistapp.local.todo.dao.TodoDao

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