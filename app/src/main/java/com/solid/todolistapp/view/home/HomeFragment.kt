package com.solid.todolistapp.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.FragmentHomeBinding
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



    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}