package com.solid.todolistapp.model.database.user

import androidx.lifecycle.LiveData
import com.solid.todolistapp.model.data.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}