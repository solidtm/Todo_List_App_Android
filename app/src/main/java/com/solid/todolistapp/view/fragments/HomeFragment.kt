package com.solid.todolistapp.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.FragmentHomeBinding
import com.solid.todolistapp.model.TodoViewModel
import com.solid.todolistapp.model.UserViewModel
import com.solid.todolistapp.view.activities.todo.adapter.TodoAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var todoViewModel: TodoViewModel
    private lateinit var userViewModel: UserViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)

        val todoAdapter = TodoAdapter()
        binding.todoListRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.todoListRv.adapter = todoAdapter

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        todoViewModel.readAllData.observe(viewLifecycleOwner, { todo ->
            todoAdapter.setData(todo)
        })


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}