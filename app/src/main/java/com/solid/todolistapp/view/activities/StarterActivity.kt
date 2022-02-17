package com.solid.todolistapp.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.solid.todolistapp.MainActivity
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.ActivityStarterBinding

class StarterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStarterBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding= ActivityStarterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        val logged = sharedPref.getBoolean("LOGGED", false)

        Toast.makeText(this, "LOGGED: $logged", Toast.LENGTH_LONG).show()

        if (logged){
            startActivity(Intent(this, MainActivity::class.java))
        }else{
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController

            val navGraph = navController.navInflater.inflate(R.navigation.starter)

            navGraph.setStartDestination(R.id.navigation_login)
            navController.graph = navGraph
        }

    }
}