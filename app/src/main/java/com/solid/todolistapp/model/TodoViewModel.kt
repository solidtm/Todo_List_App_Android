package com.solid.todolistapp.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.solid.todolistapp.model.data.Todo
import com.solid.todolistapp.model.database.todo.TodoRepository
import com.solid.todolistapp.model.database.user.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Todo>>
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
}