package com.solid.todolistapp.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.solid.todolistapp.databinding.FragmentNotificationsBinding
import com.solid.todolistapp.viewmodel.NotificationsViewModel

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentNotificationsBinding.bind(view)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}