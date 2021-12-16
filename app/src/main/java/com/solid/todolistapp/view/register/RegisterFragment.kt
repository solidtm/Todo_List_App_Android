package com.solid.todolistapp.view.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

//    private val viewModel: RegisterViewModel by viewModels()

//    val fullName = binding.userName
//    val userEmail = binding.userEmail
//    val userPassword = binding.userPassword
//    val signUpWithGoogle = binding.buttonGoogle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentRegisterBinding.bind(view)

        binding.buttonSignUp.setOnClickListener{
            Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_navigation_register_to_navigation_login)
        }

        binding.textViewSignIn.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_register_to_navigation_login)
        }
    }
}