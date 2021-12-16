package com.solid.todolistapp.view.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.ActivityStarterBinding
import com.solid.todolistapp.databinding.NewTodoActivityBinding

class NewTodoActivity : AppCompatActivity() {
    private lateinit var binding: NewTodoActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= NewTodoActivityBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)



    }
}