package com.solid.todolistapp.model.database.todo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.solid.todolistapp.model.data.Todo

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTodo(todo: Todo)

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Todo>>
}