package com.example.modern_poker1

import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity

class SplashScreenActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        val splashLogo = findViewById<ImageView>(R.id.splash_logo)

        // Start vector animation
        (splashLogo.drawable as? Animatable)?.start()

        // Delay and navigate to LoginActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000) // Adjust time as needed
    }
}