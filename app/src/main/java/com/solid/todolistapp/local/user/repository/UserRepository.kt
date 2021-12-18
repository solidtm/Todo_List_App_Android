package com.solid.todolistapp.local.user.repository

import androidx.lifecycle.LiveData
import com.solid.todolistapp.model.User
import com.solid.todolistapp.local.user.dao.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}