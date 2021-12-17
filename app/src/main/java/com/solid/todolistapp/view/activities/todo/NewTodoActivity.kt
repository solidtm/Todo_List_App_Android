package com.solid.todolistapp.view.activities.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.NewTodoActivityBinding
import com.solid.todolistapp.model.TodoViewModel
import com.solid.todolistapp.model.data.Todo

class NewTodoActivity : AppCompatActivity() {
    private lateinit var binding: NewTodoActivityBinding
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= NewTodoActivityBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        binding.saveButton.setOnClickListener{
            insertTodoToDatabase()
        }

    }

    private fun insertTodoToDatabase() {
        val taskName = binding.taskName.text.toString()
        val taskDesc = binding.taskDescription.text.toString()
        val date = binding.datePickerText.text.toString()
        val alarmSet = binding.alarmText.text.toString()
        val remindMe = binding.remindMeText.text.toString()
        val priority = binding.lowButton.text.toString()
        val category = binding.workButton.text.toString()
        val finished = false

        if (inputCheck(taskName, taskDesc)){
//            Create User Object
            val todo = Todo(0, taskName = taskName, category = category, priority = priority,
                            timeStamp = date, finished = finished)

//            Add todo to Database
            todoViewModel.addTodo(todo)

            Toast.makeText(applicationContext, "Todo Saved Successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun inputCheck(taskName: String, taskDesc: String): Boolean{
        return !(TextUtils.isEmpty(taskName) && TextUtils.isEmpty(taskDesc))
    }
}