package com.solid.todolistapp.features.register

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.FragmentRegisterBinding
import com.solid.todolistapp.data.model.User

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var userViewModel: UserViewModel

    private val EMAIL_REGEX = "^\\w+@(gmail|yahoo).com$"

//    val signUpWithGoogle = binding.buttonGoogle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentRegisterBinding.bind(view)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.buttonSignUp.setOnClickListener{
            insertDataToDatabase()
        }

        binding.textViewSignIn.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_register_to_navigation_login)

        }

        binding.buttonGoogle.setOnClickListener{

        }
    }

    private fun insertDataToDatabase() {
        val fullName = binding.userName.text.toString()
        val userEmail = binding.userEmail.text.toString()
        val userPassword = binding.userPassword.text.toString()

        if (!inputCheck(fullName, userEmail, userPassword)){
           Snackbar.make(binding.parent, "Fields cannot be empty", Snackbar.LENGTH_SHORT).show()
        }else if(emailCheck(email = userEmail)){
            Snackbar.make(binding.parent, "Email is not valid", Snackbar.LENGTH_SHORT).show()
        }else{
//            Create User Object
            val user = User(0, fullName = fullName, email = userEmail, password = userPassword)
            val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor =  sharedPref.edit()

            editor.putString("fullName", user.fullName)
            editor.putString("userEmail", user.email)
            editor.putString("userPassword", user.password)
            editor.apply()

//            Add User to Database
            userViewModel.addUser(user)

            Toast.makeText(requireContext(), "Registration Successful", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_navigation_register_to_navigation_login)
        }
    }

    private fun inputCheck(fullName: String, email: String, password: String): Boolean{
        return !(TextUtils.isEmpty(fullName) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password))
    }

    private fun emailCheck(email: String): Boolean{
        return (email.toRegex().matches(EMAIL_REGEX))
    }
}