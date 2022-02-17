package com.solid.todolistapp.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.FragmentHomeBinding
import com.solid.todolistapp.viewmodel.TodoViewModel
import com.solid.todolistapp.view.activities.todo.adapter.TodoAdapter
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var todoViewModel: TodoViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)

        val todoAdapter = TodoAdapter()
        binding.todoListRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.todoListRv.adapter = todoAdapter

        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        todoViewModel.readAllData.observe(viewLifecycleOwner) { todo ->
            todoAdapter.setData(todo)
        }

//        get the fullName from intent
        val fullName = activity?.intent?.getStringExtra("fullName")
        var name = ""
        if (fullName != null){
            name = fullName.substring(0, fullName.indexOf(" ") + 1)
        }

        binding.greetText.text = String.format("%s", "Hello, $name")

        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateInstance()
        val formattedDate = formatter.format(date)
        binding.dateText.text = String.format("%s %s", "Today, ", formattedDate)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}