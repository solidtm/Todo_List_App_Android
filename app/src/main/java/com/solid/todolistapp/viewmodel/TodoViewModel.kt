package com.solid.todolistapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.solid.todolistapp.model.Todo
import com.solid.todolistapp.local.todo.repository.TodoRepository
import com.solid.todolistapp.database.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {

     val readAllData: LiveData<List<Todo>>
    private val repository: TodoRepository

    init {
        val todoDao = UserDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        readAllData = repository.readAllData
    }

    fun addTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todo)
        }
    }

    fun updateTodo(todo: Todo){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteTodo(todo)
        }
    }
}