package com.solid.todolistapp.view.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.solid.todolistapp.R
import com.solid.todolistapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private val SHOW_SPLASH = "show times"
    private val SPLASH_VALUE = "splash is shown"
    private var splashIsShown = false
    private var showTime = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//                Set up view binding
        val splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharedPref.edit()

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

        if (sharedPref.getBoolean(SPLASH_VALUE, false)){
            startActivity(Intent(this@SplashActivity, StarterActivity::class.java))
            finish()
        }else{
            splashAnimation.setAnimationListener(object: Animation.AnimationListener{
                override fun onAnimationStart(animation: Animation?) {}   //Do nothing on animation start

                override fun onAnimationEnd(animation: Animation?) {  //Move to the next screen on end of animation after 1s using a Handler & Intent
                    Handler(Looper.getMainLooper()).postDelayed({
                        editor.putInt(SHOW_SPLASH, showTime + 1)
                        editor.putBoolean(SPLASH_VALUE, splashIsShown)
                        editor.apply()

                        val intent = Intent(this@SplashActivity, StarterActivity::class.java)
                        intent.putExtra("is_from_splash_activity", sharedPref.getInt(SHOW_SPLASH, 0))
                        startActivity(intent)
                        finish()
                    }, 1000)
                }

                override fun onAnimationRepeat(animation: Animation?) {}    //Do nothing on animation repeat
            })
            splashIsShown = true
        }

    }
}