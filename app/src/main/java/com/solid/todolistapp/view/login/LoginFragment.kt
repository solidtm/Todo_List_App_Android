package com.solid.todolistapp.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.solid.todolistapp.MainActivity
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

//    private val viewModel: SignInViewModel by viewModels()

//    val email = binding.email
//    val password = binding.password

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentLoginBinding.bind(view)

        binding.textViewRegister.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_login_to_navigation_register)
        }

        binding.buttonSignIn.setOnClickListener{
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
    }
}