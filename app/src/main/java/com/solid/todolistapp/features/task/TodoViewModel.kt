package com.solid.todolistapp.features.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.solid.todolistapp.data.model.Todo
import com.solid.todolistapp.data.repository.TodoRepository
import com.solid.todolistapp.data.db.UserDatabase
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