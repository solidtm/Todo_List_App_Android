package com.solid.todolistapp.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.solid.todolistapp.MainActivity
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val LOGGED = "logged"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentLoginBinding.bind(view)

        binding.textViewRegister.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_login_to_navigation_register)
        }

        binding.buttonSignIn.setOnClickListener{
            loginUser()
        }
    }

    private fun loginUser() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val userEmail = sharedPref.getString("userEmail", "")
        val userPassword = sharedPref.getString("userPassword", "")


        if (!(inputCheck(email, password) && (userEmail != null && userPassword != null))){
            Snackbar.make(binding.parent, "Fields cannot be empty", Snackbar.LENGTH_SHORT).show()
        }else if (email != userEmail){
            binding.layoutUserEmail.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.category_color)
            Snackbar.make(binding.parent, "Incorrect email", Snackbar.LENGTH_SHORT).show()
        }else if (password != userPassword){
            binding.layoutUserPass.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.category_color)
            Snackbar.make(binding.parent, "Incorrect password", Snackbar.LENGTH_SHORT).show()
        }else{
            if (email == userEmail && password == userPassword){
                Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.putExtra("fullName", sharedPref.getString("fullName", ""))

                startActivity(intent)
            }
        }
    }

    private fun inputCheck(email: String, password: String): Boolean {
        return !(TextUtils.isEmpty(email) && TextUtils.isEmpty(password))
    }


}