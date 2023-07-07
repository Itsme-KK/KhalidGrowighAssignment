package com.example.growighassignment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if the app has been opened before
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isFirstOpen = sharedPreferences.getBoolean("isFirstOpen", true)

        val intent = if (isFirstOpen) {
            // Launch onboarding activity if it's the first open
            sharedPreferences.edit().putBoolean("isFirstOpen", false).apply()
            Intent(this, OnBoardingActivity::class.java)
        } else {
            // Launch home activity if not the first open
            Intent(this, HomeActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}