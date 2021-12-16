package com.solid.todolistapp.view.splashscreen

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.solid.todolistapp.MainActivity
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //        Set up view binding
        val splashBinding: ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

//        Set the splash Activity screen to be full screen to hide status bar.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else{
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

//        Setup splash Animation using anim resource
        val splashAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_splash)    //Load the splash animation resource
        splashBinding.appName.animation = splashAnimation     //set the text animation
        splashAnimation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {}    //Do nothing on animation start

            override fun onAnimationEnd(animation: Animation?) {  //Move to the next screen on end of animation after 1s using a Handler & Intent
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }, 1000)
            }

            override fun onAnimationRepeat(animation: Animation?) {}    //Do nothing on animation repeat
        })
    }
}