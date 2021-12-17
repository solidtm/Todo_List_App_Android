package com.solid.todolistapp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.ActivityStarterBinding

class StarterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStarterBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding= ActivityStarterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.starter)


//        navGraph.startDestination = R.id.navigation_login
        val countFromSplashActivity = intent.getIntExtra("is_from_splash_activity", 0)
        if(countFromSplashActivity > 0) {
            navGraph.startDestination = R.id.navigation_login
        }

        navGraph.startDestination = R.id.navigation_login

        navController.graph = navGraph
    }
}