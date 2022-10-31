package com.solid.todolistapp.features.task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.solid.todolistapp.R
import com.solid.todolistapp.data.model.Todo
import com.solid.todolistapp.databinding.NewTodoActivityBinding
import com.solid.todolistapp.util.invert
import java.util.*


class NewTodoActivity : AppCompatActivity() {
    private lateinit var binding: NewTodoActivityBinding
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var timePicker: TimePickerDialog
    private lateinit var datePicker: DatePickerDialog
    private lateinit var priority: String
    private lateinit var category: String
    private var priorityColor: Int = 0
    private var categoryColor: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = NewTodoActivityBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

//        Setting a TimePicker for the alarm
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)
        val amPm: String = if (hour > 12) {
            "PM"
        } else {
            "AM"
        }
//        Setting a datePicker for the date
        val year = currentTime.get(Calendar.YEAR)
        val month = currentTime.get(Calendar.MONTH)
        val day = currentTime.get(Calendar.DAY_OF_MONTH)

        timePicker = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                binding.alarmText.text = String.format("%d : %d %s", hourOfDay, minute, amPm)
            }, hour, minute, true
        )

        datePicker = DatePickerDialog(
            this@NewTodoActivity,
            { _, year, monthOfYear, dayOfMonth ->
                binding.datePickerText.text =
                    String.format("%d/%d/%d", dayOfMonth, monthOfYear, year)
            }, year, month, day
        )

        setUpListeners()
    }

    private fun setUpListeners(){
        binding.saveButton.setOnClickListener {
            insertTodoToDatabase()
        }

        binding.backImgView.setOnClickListener {
            super.onBackPressed()
        }

        binding.datePickerText.setOnClickListener {
            datePicker.show()
        }

        binding.alarmText.setOnClickListener {
            timePicker.show()
        }

//        Setting the clickListener for the priority and category buttons

        binding.lowButton.setOnClickListener {
            binding.lowButton.invert(
                true,
                R.color.white,
                R.color.secondary_dark_color,
                R.color.secondary_dark_color,
                R.color.white
            )

            binding.mediumButton.invert(
                false,
                R.color.white,
                R.color.black,
                R.color.primaryDarkColor,
                R.color.white
            )

            binding.highButton.invert(
                false,
                R.color.white,
                R.color.category_color,
                R.color.category_color,
                R.color.white
            )

            priority = binding.lowButton.text.toString()
            priorityColor = R.color.checked
        }

        binding.mediumButton.setOnClickListener {
            binding.lowButton.invert(
                false,
                R.color.white,
                R.color.secondary_dark_color,
                R.color.secondary_dark_color,
                R.color.white
            )

            binding.mediumButton.invert(
                true,
                R.color.white,
                R.color.black,
                R.color.primaryDarkColor,
                R.color.white
            )

            binding.highButton.invert(
                false,
                R.color.white,
                R.color.category_color,
                R.color.category_color,
                R.color.white
            )

            priority = binding.mediumButton.text.toString()
            priorityColor = R.color.category
        }

        binding.highButton.setOnClickListener {
            binding.lowButton.invert(
                false,
                R.color.white,
                R.color.secondary_dark_color,
                R.color.secondary_dark_color,
                R.color.white
            )

            binding.mediumButton.invert(
                false,
                R.color.white,
                R.color.black,
                R.color.primaryDarkColor,
                R.color.white
            )

            binding.highButton.invert(
                true,
                R.color.white,
                R.color.category_color,
                R.color.category_color,
                R.color.white
            )

            priority = binding.highButton.text.toString()
            priorityColor = R.color.category_color
        }

        binding.workButton.setOnClickListener {
            binding.workButton.invert(
                true,
                R.color.primaryColor,
                R.color.priority,
                R.color.priority,
                R.color.primaryColor
            )

            binding.familyButton.invert(
                false,
                R.color.family_background,
                R.color.family,
                R.color.family,
                R.color.family_background
            )

            binding.schoolButton.invert(
                false,
                R.color.school,
                R.color.school_text,
                R.color.school_text,
                R.color.school
            )

            category = binding.workButton.text.toString()
            categoryColor = R.color.category
        }

        binding.familyButton.setOnClickListener {
            binding.workButton.invert(
                false,
                R.color.primaryColor,
                R.color.priority,
                R.color.priority,
                R.color.primaryColor
            )

            binding.familyButton.invert(
                true,
                R.color.family_background,
                R.color.family,
                R.color.family,
                R.color.family_background
            )

            binding.schoolButton.invert(
                false,
                R.color.school,
                R.color.school_text,
                R.color.school_text,
                R.color.school
            )
            category = binding.familyButton.text.toString()
            categoryColor = R.color.family_background
        }

        binding.schoolButton.setOnClickListener {
            binding.workButton.invert(
                false,
                R.color.primaryColor,
                R.color.priority,
                R.color.priority,
                R.color.primaryColor
            )

            binding.familyButton.invert(
                false,
                R.color.family_background,
                R.color.family,
                R.color.family,
                R.color.family_background
            )

            binding.schoolButton.invert(
                true,
                R.color.school,
                R.color.school_text,
                R.color.school_text,
                R.color.school
            )
            category = binding.schoolButton.text.toString()
            categoryColor = R.color.school
        }
    }

    private fun insertTodoToDatabase() {
        val taskName = binding.taskName.text.toString()
        val taskDesc = binding.taskDescription.text.toString()
        val date = binding.datePickerText.text.toString()       //to be used in calendar screen
        val alarmSet = binding.alarmText.text.toString()
        val remindMe = binding.remindMeText.text.toString()     //to be used in alarms
        val finished = false

        if (inputCheck(taskName, taskDesc)) {
//            Create Todo Object
            val todo = Todo(
                0,
                taskName = taskName,
                category = category,
                priority = priority,
                timeStamp = alarmSet,
                finished = finished,
                priorityCardColor = priorityColor,
                categoryCardColor = categoryColor
            )

//            Add todo to Database
            todoViewModel.addTodo(todo)

            Toast.makeText(applicationContext, "Todo Saved Successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun inputCheck(taskName: String, taskDesc: String): Boolean {
        return !(TextUtils.isEmpty(taskName) && TextUtils.isEmpty(taskDesc))
    }
}





