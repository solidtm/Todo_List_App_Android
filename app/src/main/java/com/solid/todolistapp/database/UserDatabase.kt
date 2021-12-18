package com.solid.todolistapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.solid.todolistapp.local.todo.dao.TodoDao
import com.solid.todolistapp.local.user.dao.UserDao
import com.solid.todolistapp.model.Todo
import com.solid.todolistapp.model.User

@Database(entities = [User::class, Todo::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun todoDao(): TodoDao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}