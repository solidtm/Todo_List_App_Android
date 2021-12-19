package com.solid.todolistapp.view.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.FragmentHomeBinding
import com.solid.todolistapp.viewmodel.TodoViewModel
import com.solid.todolistapp.viewmodel.UserViewModel
import com.solid.todolistapp.view.activities.todo.adapter.TodoAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var todoViewModel: TodoViewModel

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

        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val fullName = sharedPref.getString("fullName", "")
        val name = TextUtils.substring(fullName, 0, fullName!!.indexOf(" "))
        binding.greetText.text = String.format("%s", "Hello, $name")

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}