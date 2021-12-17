package com.solid.todolistapp.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.FragmentHomeBinding
import com.solid.todolistapp.model.data.Todo
import com.solid.todolistapp.view.activities.todo.adapter.TodoAdapter
import com.solid.todolistapp.viewmodel.home.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

//    val greetText = binding.greetingText
//    val dateText = binding.dateText
//    val todoRv = binding.todoListRv
//    val searchIcon = binding.searchImgView
//    val profilePic = binding.profileImgView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)

        initAdapter()

    }

    private fun initAdapter() {

        val todoList = listOf(
            Todo(1, "Create wireframes", "Work", "High", "10:00PM", false),
            Todo(2, "Test Prototypes", "Work", "High", "2:00PM", false),
            Todo(3, "Call Mum", "Family", "Low", "6:00PM", false),
            Todo(4, "Study Mathematics", "School", "Medium", "4:00PM", true),
            Todo(5, "Dinner with Pedro", "Family", "Low", "8:00PM", true)
        )

        val todoAdapter = TodoAdapter(todoList)

        binding.todoListRv.also {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            it.adapter = todoAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}