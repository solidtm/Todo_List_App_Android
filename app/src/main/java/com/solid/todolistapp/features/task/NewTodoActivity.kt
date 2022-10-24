package com.solid.todolistapp.features.task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.NewTodoActivityBinding
import com.solid.todolistapp.data.model.Todo
import java.util.*

class NewTodoActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: NewTodoActivityBinding
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var timePicker: TimePickerDialog
    private lateinit var datePicker: DatePickerDialog
    private lateinit var priority: String
    private lateinit var category: String
    private var priorityColor: Int = 0
    private var categoryColor: Int = 0
    private var activePriorityBtn: String = ""   //for detecting and changing priority active button
    private var activeCategoryBtn: String = ""  //for detecting and changing category active button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= NewTodoActivityBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        binding.saveButton.setOnClickListener{
            insertTodoToDatabase()
        }

        binding.backImgView.setOnClickListener{
            super.onBackPressed()
        }

//        Setting a TimePicker for the alarm
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)
        val amPm: String = if(hour > 12) {
            "PM"
        } else {
            "AM"
        }
//        Setting a datePicker for the date
        val year = currentTime.get(Calendar.YEAR)
        val month = currentTime.get(Calendar.MONTH)
        val day = currentTime.get(Calendar.DAY_OF_MONTH)

        timePicker = TimePickerDialog(this,
            {_, hourOfDay, minute -> binding.alarmText.text = String.format("%d : %d %s", hourOfDay, minute, amPm)}, hour, minute, true)

        datePicker = DatePickerDialog(this@NewTodoActivity,
            {_, year, monthOfYear, dayOfMonth ->
                binding.datePickerText.text = String.format("%d/%d/%d", dayOfMonth, monthOfYear, year)
            }, year, month, day)

        binding.datePickerText.setOnClickListener{
            datePicker.show()
        }

        binding.alarmText.setOnClickListener{
            timePicker.show()
        }

//        Setting the clickListener for the priority and category buttons
        binding.lowButton.setOnClickListener(this)
        binding.mediumButton.setOnClickListener(this)
        binding.highButton.setOnClickListener(this)
        binding.workButton.setOnClickListener(this)
        binding.familyButton.setOnClickListener(this)
        binding.schoolButton.setOnClickListener(this)

    }

    override fun onClick(v: View){
        when(v.id){
            R.id.low_button -> {
                priority = binding.lowButton.text.toString()
                priorityColor = R.color.checked
                activePriorityBtn = "low_button"
            }
            R.id.medium_button -> {
                priority = binding.mediumButton.text.toString()
                priorityColor = R.color.category
                activePriorityBtn = "medium_button"
            }
            R.id.high_button -> {
                priority = binding.highButton.text.toString()
                priorityColor = R.color.category_color
                activePriorityBtn = "high_button"
            }
            R.id.work_button -> {
                category = binding.workButton.text.toString()
                categoryColor = R.color.category
                activeCategoryBtn = "work_button"
            }
            R.id.family_button -> {
                category = binding.familyButton.text.toString()
                categoryColor = R.color.family_background
                activeCategoryBtn = "family_button"
            }
            R.id.school_button -> {
                category = binding.schoolButton.text.toString()
                categoryColor = R.color.school
                activeCategoryBtn = "school_button"
            }
        }
        changeActiveBtnColor()
    }

    private fun changeActiveBtnColor(){
        when (activePriorityBtn) {
            "low_button" -> {
                binding.lowButton.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.lowButton.setBackgroundColor(ContextCompat.getColor(this, R.color.secondary_color))

                binding.mediumButton.setTextColor(ContextCompat.getColor(this, R.color.black))
                binding.mediumButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

                binding.highButton.setTextColor(ContextCompat.getColor(this, R.color.category_color))
                binding.highButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

            }
            "medium_button" -> {
                binding.mediumButton.setBackgroundColor(ContextCompat.getColor(this, R.color.primaryDarkColor))
                binding.mediumButton.setTextColor(ContextCompat.getColor(this, R.color.white))

                binding.lowButton.setTextColor(ContextCompat.getColor(this, R.color.secondary_dark_color))
                binding.lowButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

                binding.highButton.setTextColor(ContextCompat.getColor(this, R.color.category_color))
                binding.highButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            }
            "high_button" -> {
                binding.mediumButton.setTextColor(ContextCompat.getColor(this, R.color.black))
                binding.mediumButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

                binding.lowButton.setTextColor(ContextCompat.getColor(this, R.color.secondary_dark_color))
                binding.lowButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

                binding.highButton.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.highButton.setBackgroundColor(ContextCompat.getColor(this, R.color.category_color))
            }
        }

        when (activeCategoryBtn) {
            "work_button" -> {
                binding.workButton.setStrokeColorResource(R.color.primaryDarkColor)
                binding.workButton.setTextColor(ContextCompat.getColor(this, R.color.primaryDarkColor))
                binding.workButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

                binding.familyButton.setStrokeColorResource(R.color.white)
                binding.familyButton.setTextColor(ContextCompat.getColor(this, R.color.family))
                binding.familyButton.setBackgroundColor(ContextCompat.getColor(this, R.color.family_background))

                binding.schoolButton.setStrokeColorResource(R.color.white)
                binding.schoolButton.setTextColor(ContextCompat.getColor(this, R.color.school_text))
                binding.schoolButton.setBackgroundColor(ContextCompat.getColor(this, R.color.school))
            }
            "family_button" -> {
                binding.familyButton.setStrokeColorResource(R.color.family)
                binding.familyButton.setTextColor(ContextCompat.getColor(this, R.color.family))
                binding.familyButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

                binding.workButton.setStrokeColorResource(R.color.white)
                binding.workButton.setTextColor(ContextCompat.getColor(this, R.color.primaryDarkColor))
                binding.workButton.setBackgroundColor(ContextCompat.getColor(this, R.color.primaryColor))

                binding.schoolButton.setStrokeColorResource(R.color.white)
                binding.schoolButton.setTextColor(ContextCompat.getColor(this, R.color.school_text))
                binding.schoolButton.setBackgroundColor(ContextCompat.getColor(this, R.color.school))
            }
            "school_button" -> {
                binding.schoolButton.setStrokeColorResource(R.color.school_text)
                binding.schoolButton.setTextColor(ContextCompat.getColor(this, R.color.school_text))
                binding.schoolButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

                binding.familyButton.setStrokeColorResource(R.color.white)
                binding.familyButton.setTextColor(ContextCompat.getColor(this, R.color.family))
                binding.familyButton.setBackgroundColor(ContextCompat.getColor(this, R.color.family_background))

                binding.workButton.setStrokeColorResource(R.color.white)
                binding.workButton.setTextColor(ContextCompat.getColor(this, R.color.primaryDarkColor))
                binding.workButton.setBackgroundColor(ContextCompat.getColor(this, R.color.primaryColor))
            }
        }
    }

    private fun insertTodoToDatabase() {
        val taskName = binding.taskName.text.toString()
        val taskDesc = binding.taskDescription.text.toString()
        val date = binding.datePickerText.text.toString()       //to be used in calendar screen
        val alarmSet = binding.alarmText.text.toString()
        val remindMe = binding.remindMeText.text.toString()     //to be used in alarms
        val finished = false

        if (inputCheck(taskName, taskDesc)){
//            Create Todo Object
            val todo = Todo(0, taskName = taskName, category = category, priority = priority,
                            timeStamp = alarmSet, finished = finished, priorityCardColor = priorityColor, categoryCardColor = categoryColor)

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