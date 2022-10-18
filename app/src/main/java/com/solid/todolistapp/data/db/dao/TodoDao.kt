package com.solid.todolistapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.solid.todolistapp.data.model.Todo

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTodo(todo: Todo)

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Todo>>

    @Update
    fun updateTodo(todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)
}