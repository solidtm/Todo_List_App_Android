package com.solid.todolistapp.view.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.solid.todolistapp.databinding.FragmentCalendarBinding
import com.solid.todolistapp.model.TodoViewModel
import com.solid.todolistapp.viewmodel.calendar.CalendarViewModel

class CalendarFragment : Fragment() {

    private val args by navArgs<CalendarFragmentArgs>()
    private lateinit var todoViewModel: TodoViewModel
    private var _binding: FragmentCalendarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentCalendarBinding.bind(view)

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        binding.taskName.setText(args.currentTodo.taskName)
        binding.categoryText.text = args.currentTodo.category
        binding.priorityText.text = args.currentTodo.priority
        binding.alarmText.text = args.currentTodo.timeStamp
        val priorityCardColor = args.currentTodo.categoryCardColor
        val categoryCardColor = args.currentTodo.priorityCardColor

        binding.updateButton.setOnClickListener{
//            Create Updated todo
        }
    }

    private fun updateTodo(){
        val taskName = binding.taskName.text.toString()
        val categoryText = binding.categoryText.text.toString()
        val priorityText = binding.priorityText.text.toString()
        val alarmText = binding.alarmText.text.toString()

        if (inputCheck(taskName, categoryText, priorityText, alarmText)){

        }
    }

    private fun inputCheck(taskName: String, categoryText: String, priorityText: String, alarmText: String): Boolean {
        return !(TextUtils.isEmpty(taskName) && TextUtils.isEmpty(categoryText) && TextUtils.isEmpty(priorityText) && TextUtils.isEmpty(alarmText))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}